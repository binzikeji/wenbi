package com.contaat.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 胡字蒙.
 * @Date 2018/11/22   15:26
 */
@RestController
public class TaLogin {
    @RequestMapping("/login")
    public String Login(){
        return "HelloWorld";
    }


}
