package com.zhangeaky.application.bootstrap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @RequestMapping("test")
    public String test() {
        return "hello world";
    }

}
