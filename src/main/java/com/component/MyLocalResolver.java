package com.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @ClassName MyLocalResolver
 * @Description 可以在链接上携带区域信息
 * @Author Administrator
 * @Date 2018/12/11 17:20
 * @Version 1.0
 **/
public class MyLocalResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = new Locale("zh","CN");
        String l = request.getParameter("l");
        if(!StringUtils.isEmpty(l)){
            String[] strings = l.split("_");
            locale = new Locale(strings[0],strings[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
