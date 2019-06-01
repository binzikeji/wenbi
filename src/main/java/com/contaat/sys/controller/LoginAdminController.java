package com.contaat.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.contaat.sys.entity.User;
import com.contaat.sys.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 胡字蒙.
 * @Date 2018/12/20   14:20
 */
@Controller
@RequestMapping(value = "${started}")
public class LoginAdminController {
    @RequestMapping(value = "/loginadmin")
    public String LoginAdmin(){
        return "index/loginadmin";
    }
    private String CHECKSQL = "^(.+)\\sand\\s(.+)|(.+)\\sor(.+)\\s$";
    @Autowired
    public com.contaat.sys.service.UserService UserService;
    @ApiOperation(value = "当前用户登陆",notes = "当前用户登陆")
    @PostMapping(value = "/Login")
    public  String  adminLogin(User user, Model model){
        User userOne =  UserService.getOne(new QueryWrapper<User>().setEntity(user));
        if(userOne == null){
            model.addAttribute("msg","登陆失败");
            return "index/loginadmin";
        }
        model.addAttribute("users",userOne);
        return "adminview/zhuye";
    }

}
