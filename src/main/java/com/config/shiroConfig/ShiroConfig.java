package com.config.shiroConfig;


import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author 胡字蒙.
 * @Date 2018/12/20   10:26
 */

@Configuration
public class ShiroConfig {
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(manager);


        //拦截之后跳转页面
        shiroFilterFactoryBean.setLoginUrl("/f/index");

        Map<String,String> filterMap = new LinkedHashMap<String, String>();
        //添加shiro 内置过滤器
//        /*shiro内置过滤器 ，可以实现权限相关的拦截器
//        * 常用的过滤器：
//        *       anon: 无需认证
//        *       authc: 必须认证
//        *       user: 如果使用remeberMe的功能才可以访问
//        *       perms: 该资源必须得到资源授权
//        *       role ： 该资源必须得到角色授权才可以访问
//        * */
        //安全退出
        filterMap.put("/f/admin/login", "logout");
        // 过滤链
        filterMap.put("/asserts/**", "anon");
        filterMap.put("/common/**", "anon");
        filterMap.put("/layer/**", "anon");
        filterMap.put("/qianduan/**", "anon");
        filterMap.put("/zhuyeAsserts/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/webscan_360_cn.html", "anon");

        filterMap.put("/f/**","anon");
        filterMap.put("/a/**","authc");
//        filterMap.put("/f","anon");
//        //授权过滤器
//        filterMap.put("/a/*","authc");
        //设置未授权提示页面
        //shiroFilterFactoryBean.setUnauthorizedUrl("/f/unAuth");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(userRealm);
        return manager;
    }
    //配置自定义的权限登录器
    @Bean(name="userRealm")
    public UserRealm userRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        UserRealm userRealm=new UserRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }
    //配置自定义的密码比较器
    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }
    //配置声明周期
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
    /**
     * 配置Shiro 缓存
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return cacheManager;
    }
    /**
     * Shiro生命周期处理器 * @return
     */

}
