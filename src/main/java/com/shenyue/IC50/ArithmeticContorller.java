package com.shenyue.IC50;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Controller
@RequestMapping("/arithmetic")
public class ArithmeticContorller {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public HashMap getTest(){
        HashMap test = new HashMap();
        test.put("test","success");
        return test;
    }


}
