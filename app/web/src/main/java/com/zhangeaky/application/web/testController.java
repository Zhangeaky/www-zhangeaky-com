package com.zhangeaky.application.web;

import com.zhangeaky.application.web.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @Autowired
    SampleService sampleService;

    @RequestMapping("test")
    public String test() {
        return "hello world";
    }

}
