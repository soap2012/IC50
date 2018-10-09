package com.shenyue.IC50;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResultDTO<TableData<String>> uploadFile(@RequestParam(value = "file")MultipartFile file){
        return uploadFileService.uploadFile(file);
    }


}
