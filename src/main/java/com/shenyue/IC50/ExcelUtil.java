package com.shenyue.IC50;

public class ExcelUtil {
//    private ExcelUtil(){}

    public static boolean isExcel2007(String fileName){
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        return fileType.equals(".xlsx");
    }

    public static boolean isExcel2003(String fileName){
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        return fileType.equals(".xls");
    }


//    public static final ExcelUtil getInstance() {
//        return ExcelUtilHolder.instance;
//    }
//
//    private static final class ExcelUtilHolder {
//        private static final ExcelUtil instance = new ExcelUtil();
//    }

}
