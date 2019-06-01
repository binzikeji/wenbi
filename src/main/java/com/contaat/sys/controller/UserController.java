package com.contaat.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author 胡胡道人
 * @since 2018-12-17
 */
@Controller
@RequestMapping("{stopped}/admin/sys/user")
public class UserController {
        @RequestMapping(value = "/indexshouye")
        public String loginadminshouye(){
                return "adminview/index_shouye";
        }
}
