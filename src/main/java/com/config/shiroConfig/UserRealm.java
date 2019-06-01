package com.config.shiroConfig;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.contaat.sys.entity.Kehu;
import com.contaat.sys.service.KehuService;
import com.contaat.sys.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author 胡字蒙.
 * @Date 2018/12/20   10:31
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private KehuService kehuService;
    @Autowired
    private UserService userService;
    /*执行授权逻辑*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑");
        return null;
    }
    /*执行认证逻辑*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //判断用户名密码是否正确
        // 将token装换成UsernamePasswordToken
        UsernamePasswordToken usertoken = (UsernamePasswordToken) token;
        Kehu kehuOne =  kehuService.getOne(new QueryWrapper<Kehu>().lambda()
                                            .eq(Kehu::getLoginCode,usertoken.getUsername())
                                            );
        //.eq(Kehu::getPassword,usertoken.getPassword())
        if(kehuOne == null){
            return null;
        }
        String id=kehuOne.getId();
        if("0".equals(kehuOne.getStatus())){
            id="stop";
        }
        if("admin".equals(kehuOne.getStatus())){
            id="admin";
        }
        return new SimpleAuthenticationInfo(id,kehuOne.getPassword(),this.getName());
    }
}
