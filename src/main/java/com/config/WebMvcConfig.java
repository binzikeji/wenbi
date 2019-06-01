package com.config;

import com.component.LoginHandlerInterceptor;
import com.component.MyLocalResolver;
import com.contaat.sys.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @ClassName MyMvcConfig
 * @Description MyMvcConfig
 * @Author Administrator
 * @Date 2018/12/11 15:09
 * @Version 1.0
 **/
//使用WebMvcConfigurerAdapter可以来拓展SpringMvc功能
//@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/abc").setViewName("success");
//        //super.addViewControllers(registry);
//    }
    @Autowired

    /**
     * 自定义登录拦截器
     * @return 登录验证拦截器
     */
    @Bean
    public LoginHandlerInterceptor loginHandlerInterceptor(){
        return new LoginHandlerInterceptor();
    }

    /**
     *
     * @param registry 配置静态资源放行
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //资源映射
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
    }

    /**
     *
     * @param registry 添加拦截器
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //添加登录处理拦截器，拦截所有请求，登录除外
//        InterceptorRegistration interceptorRegistration = registry.addInterceptor(loginHandlerInterceptor());
//        //排除策略
//        interceptorRegistration.excludePathPatterns("/");
//        interceptorRegistration.excludePathPatterns("/zhuye.html");
//        interceptorRegistration.excludePathPatterns("/index");
//        interceptorRegistration.excludePathPatterns("/user/login");
//        interceptorRegistration.excludePathPatterns("/asserts/**");
//        interceptorRegistration.excludePathPatterns("/webjars/**");
//        interceptorRegistration.excludePathPatterns("/qianduan/**");
//        //拦截策略
//        interceptorRegistration.addPathPatterns("/**");
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index/index");
        //registry.addViewController("/main.html").setViewName("dashboard");
    }

    /**
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }
}
