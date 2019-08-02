package com.shenyue.IC50;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;

@Controller
@RequestMapping("/arithmetic")
public class ArithmeticContorller {

    @Autowired
    UploadFileService uploadFileService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<HashMap> getTest(){
        ResultDTO<HashMap> resultDTO = new ResultDTO<>();
        HashMap test = new HashMap();
        test.put("test","success");
        resultDTO.setData(test);
        resultDTO.setCode(200);
        resultDTO.setMsg("success");
        return resultDTO;
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(Model model){
        return "/";
    }

    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<TableData<Double>> uploadFile(@RequestParam(value = "file")MultipartFile file){
        return uploadFileService.uploadFile(file);
    }

    @RequestMapping(value = "/downloadTemplate",method = RequestMethod.GET)
    // /arithmetic/downloadTemplate
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response){
        try {
            String fileName="template.xlsx";
            InputStream inputStream = (InputStream) this.getClass().getClassLoader().getResourceAsStream(fileName);
            response.setContentType("application/zip");
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            int b = 0;
            byte[] buffer = new byte[1000000];
            while (b != -1) {
                b = inputStream.read(buffer);
                if(b!=-1) out.write(buffer, 0, b);
            }
            inputStream.close();
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            //logger.error("模板下载失败");
        }
    }


}
