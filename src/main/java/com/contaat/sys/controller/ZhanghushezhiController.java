package com.contaat.sys.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.config.Common.utils.StringUtil;
import com.contaat.sys.entity.Kehu;
import com.contaat.sys.service.KehuService;
import com.contaat.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author 胡胡道人
 * @since 2018-12-17
 */
@Controller
@RequestMapping("{stopped}")
public class ZhanghushezhiController {
        @Autowired
        private KehuService kehuService;

    /**
     *
     * @return
     */
    @GetMapping("/gerenziliao/{id}")
    public String gerenziliao(Kehu kehu, Model model){
        kehu = kehuService.getOne(new QueryWrapper<Kehu>().lambda().setEntity(kehu));
        model.addAttribute("kehu",kehu);
        model.addAttribute("celan","0");
        return "qianduan/gerenziliao";
    }

    @PostMapping("/xiugaigerenxinxi")
    public String xiugaigerenxinxi(Kehu kehu,Model model){
        String loginName = kehu.getLoginName();
        String phone = kehu.getPhone();
        String email = kehu.getEmail();
        String id = kehu.getId();
        boolean matches = false;
        if (!StringUtils.isEmpty(id)){
            kehu = kehuService.getOne(new QueryWrapper<Kehu>().lambda().eq(Kehu::getId,id));
            //昵称
            if(!StringUtils.isEmpty(loginName)){
                matches = loginName.matches("^[\\u4E00-\\u9FA5A-Za-z0-9。，、]+$");
                if (matches) {
                    kehu.setLoginName(loginName);
                } else {
                    model.addAttribute("masg", "只允许中文英文数字部分中文符号。，、组合");
                }
            }else {
                matches = false;
                model.addAttribute("masg","昵称不能为空");
            }
            //手机号
            if(!StringUtils.isEmpty(phone)){
                matches = phone.matches("1[3|5|7|8|][0-9]{9}$");
                if (matches) {
                    kehu.setPhone(phone);
                } else {
                    model.addAttribute("masg", "请输入有效的手机号码");
                }
            }else {
                matches = false;
                model.addAttribute("masg","手机号不能为空");
            }
            //邮箱
            if(!StringUtils.isEmpty(email)){
                matches = email.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
                if (matches) {
                    kehu.setEmail(email);
                } else {
                    model.addAttribute("masg", "请输入有效的邮箱");
                }
            }else {
                matches = false;
                model.addAttribute("masg","邮箱不能为空");
            }
            if (matches) matches = kehuService.updateById(kehu);
            if (matches) model.addAttribute("masg","保存成功");
            model.addAttribute("matches",matches);
        } else {
            model.addAttribute("masg","未知错误请联系管理员，邮箱3166688833@qq.com");
        }
        model.addAttribute("kehu",kehu);
        model.addAttribute("celan","0");
        return "qianduan/gerenziliao";
    }

    /**
     *
     * @param kehu
     * @param model
     * @return
     */
    @GetMapping("/chongzhimima/{id}")
    public String chongzhimima(Kehu kehu, Model model){
        kehu = kehuService.getOne(new QueryWrapper<Kehu>().lambda().setEntity(kehu));
        model.addAttribute("kehu",kehu);
        model.addAttribute("celan","1");
        return "qianduan/chongzhimima";
    }

    /**
     *
     * @param kehu
     * @param model
     * @return
     */
    @PostMapping("/xiugaimima")
    public String xiugaimima(Kehu kehu,Model model,String newPassword,String newConfirmPassword){
         Boolean  matches = false;
         String password = kehu.getPassword();
         kehu = kehuService.getById(kehu.getId());
        //旧密码（未加密）kehu.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(kehu.getLoginCode(),password);
        model.addAttribute("celan","1");
        model.addAttribute("kehu",kehu);
        try {
            //更改成功
            subject.login(token);
            //更改密码
            ByteSource salt = ByteSource.Util.bytes(kehu.getLoginCode());
            newPassword = new SimpleHash("MD5",newPassword,salt,1024).toHex();
            kehu.setPassword(newPassword);
            Boolean bl = kehuService.updateById(kehu);
            if(!bl){
                model.addAttribute("masg","修改失败，请联系管理员");
                model.addAttribute("matches",matches);
                return "qianduan/chongzhimima";
            }
            //告知前台成功
            matches=true;
            model.addAttribute("masg","修改成功");
            model.addAttribute("matches",matches);
        }catch (IncorrectCredentialsException e){
            matches=false;
            model.addAttribute("matches",matches);
            model.addAttribute("masg","旧密码错误");
            return "qianduan/chongzhimima";
        }
        //新密码newPassword 第二次新密码newConfirmPassword\
        model.addAttribute("kehu",kehu);
        return "qianduan/chongzhimima";
    }
}
