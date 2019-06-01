package com.contaat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author 胡字蒙.
 * @Date 2018/11/23   10:47
 */

@Controller
public class jspController {

    @RequestMapping("/jsptest")
    public String jspIndex(Model model){
       /* int y = 1 / 0; //异常测试*/
        model.addAttribute("user","胡字蒙");
        return "index";
    }
}
