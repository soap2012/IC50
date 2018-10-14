package com.shenyue.IC50;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@Service
public class UploadFileService {
    public ResultDTO<TableData<Double>> uploadFile(MultipartFile file){
        ResultDTO<TableData<Double>> resultDTO = new ResultDTO<>();
        //初始化输入流
        InputStream is = null;
        String fileName = file.getOriginalFilename();
        try{
            //根据新建的文件实例化输入流
            is = file.getInputStream();
            //根据版本选择创建workbook的方式
            Workbook wb = null;
            if(ExcelUtil.isExcel2007(fileName)){
                wb = new XSSFWorkbook(is);
            }else if(ExcelUtil.isExcel2003(fileName)){
                wb = new HSSFWorkbook(is);
            }else{
                resultDTO.setMsg("文件类型错误");
                resultDTO.setCode(400);
            }
            Sheet sheet = wb.getSheetAt(0);


            List<List<Double>> table = new LinkedList<>();

            //将数据存入table中
            for(int r=1; r <= sheet.getLastRowNum();r++){
                List<Double> tableRow = new LinkedList<>();
                Row row = sheet.getRow(r);
                if(row == null){
                    continue;
                }
                //获取总列数
                int columnNums = row.getPhysicalNumberOfCells();
                for(int col=0;col< columnNums;col++){
                    if(row.getCell(col) != null){
                        row.getCell(col).setCellType(CellType.NUMERIC);
                        tableRow.add(row.getCell(col).getNumericCellValue());
                    }
                    //sum.set(col,sum.get(col)+row.getCell(col).getNumericCellValue());
                }
                table.add(tableRow);
            }
            DataProcess process = new DataProcess();
            TableData<Double> data = process.dataProcess(table);
            resultDTO.setData(data);
            resultDTO.setCode(200);
            resultDTO.setMsg("success");

        }catch (Exception e){
            resultDTO.setCode(400);
            resultDTO.setMsg("解析excel失败："+e.getMessage());

        }finally {
            if(is != null){
                try {
                    is.close();
                }catch (IOException e){
                    is = null;
                    e.printStackTrace();
                    resultDTO.setMsg("关闭excel失败："+e.getMessage());
                }
            }
        }
        return resultDTO;
    }
}
