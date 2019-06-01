package com.contaat.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.contaat.sys.entity.Kehu;
import com.contaat.sys.entity.WrText;
import com.contaat.sys.service.KehuService;
import com.contaat.sys.service.UserService;
import com.contaat.sys.service.WrTextService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @ClassName LoginController
 * @Description LoginController
 * @Author Administrator
 * @Date 2018/12/12 10:41
 * @Version 1.0
 **/
@Controller
@RequestMapping("${started}")
public class LoginController {
    @Autowired
    private KehuService kehuService;
    @Autowired
    private WrTextService wrTextService;
    @ApiOperation(value = "获取当前用户",notes = "获取当前用户信息")
    @PostMapping(value = "/kehulogin")
    public  String  userlogin(Kehu kehu, Model model){
        //获取Subject
        Subject subject =  SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(kehu.getLoginCode(),kehu.getPassword());
        String id="";
        //执行登陆方法
        try {
            subject.login(token);
            //token.setRememberMe(true);

//            if (subject.isAuthenticated()) {
//                model.addAttribute("msg","账号已经登陆");
//                return "index/login";
//            }
            //登陆成功
            id = (String)subject.getPrincipal();
            if("stop".equals(id)){
                model.addAttribute("msg","账号停用，联系管理员");
                return "index/login";
            }
            if("admin".equals(id)){
                Kehu userOne =  kehuService.getOne(new QueryWrapper<Kehu>().lambda()
                        .eq(Kehu::getLoginCode,"admin"));
                if(userOne == null){
                    model.addAttribute("msg","登陆失败");
                    return "index/login";
                }
                model.addAttribute("users",userOne);
                return "adminview/zhuye";
            }
        }catch (UnknownAccountException e){
            //登陆失败
            model.addAttribute("msg","账号或密码错误");
            return "index/login";
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
            model.addAttribute("msg","账号或密码错误");
            return "index/login";
        }
        return "redirect:/a/WrTextList/"+id;
    }

    @ApiOperation(value = "用户注册",notes = "用户注册")
    @PostMapping(value = "/saveUser")
    public String Insert(@RequestParam("phone") String phone,@RequestParam("email") String email,
                         @RequestParam("loginName") String loginName,@RequestParam("password") String password,
                         @RequestParam("loginCode") String loginCode,Model model){
        Kehu kehu = new Kehu();
        Date date = new Date();
        kehu.setString1(password);
        ByteSource salt = ByteSource.Util.bytes(loginCode);
        password = new SimpleHash("MD5",password,salt,1024).toHex();
        kehu.setPassword(password);
        kehu.setLoginCode(loginCode);
        kehu.setStatus("1");
        kehu.setEmail(email).setPhone(phone).setLoginName(loginName);
        kehu.setCreateDate(date).setCreateBy(loginCode);
        Boolean br =  kehuService.save(kehu);
        if(!br){
            model.addAttribute("msg","账号注册失败，请重新注册。");
            return "index/zhuce";
        }
        if(kehu.getId()!=null){
            WrText wrText = new WrText();
            wrText.setText("欢迎使用【文笔】，祝您使用愉快");//文本
            wrText.setTextName("【文笔】文件名称");//标题
            wrText.setRemarks("这是备注");//备注
            wrText.setStatus("1");
            wrText.setCreateDate(date);
            wrText.setUpdateDate(date);
            wrText.setKehuid(kehu.getId());
            wrTextService.saveOrUpdate(wrText);
        }
        model.addAttribute("msg","账号注册成功。");
        return "index/login";
    }


    @ApiOperation(value = "校检用户",notes = "校检用户")
    @ResponseBody
    @PostMapping(value = "/saveuserajax")
    public String saveajax(@RequestParam("password") String password,
                           @RequestParam("loginCode") String loginCode,
                           @RequestParam("querenpassword") String querenpassword,Model model){
        if(loginCode.length() < 8 && loginCode.length()>16){
            return "用户名不可低于8位和高于16位";
        }
        Kehu kehu = new Kehu();
        kehu=kehuService.getOne(new QueryWrapper<Kehu>().eq("loginCode",loginCode));
        if(kehu != null){
            return "账号已经注册";
        }
        if(password.length() < 6 && password.length() > 16){
            return "密码不可低于6位和高于16位";
        }
        if(querenpassword.length() > 6){
            if(!querenpassword.equals(password)){
                return "两次密码不一致";
            }
        }
        return "true";
    }

    /*@PostMapping(value = "/logout")
    public String logout(){
        //subject.logout();
        return "";
    }*/

    @ResponseBody
    @RequestMapping("/isUsername")
    public Map<String, Object> isUsername(@RequestParam("loginCode")String loginCode) {
        Map<String, Object> map = new HashMap<>();
       Kehu  kehu=  kehuService.getOne(new QueryWrapper<Kehu>().lambda()
                                        .eq(Kehu::getLoginCode,loginCode));
        if (kehu == null) {
            map.put("valid", true);
        } else {
            map.put("valid", false);
        }
        return map;
    }
    //检测用户是否填写资料
    public String information(String id){
        String msg = "0";
        Kehu kehu =  kehuService.getById(id);
        //用户名 必填
        if(kehu.getLoginName() == null){
            msg="请先完善用户信息";
        }
        //手机号 必填
        if(kehu.getPhone() == null){
            msg="请先完善用户信息";
        }
        return msg;
    }
}
