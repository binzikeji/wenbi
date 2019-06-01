package com.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @ClassName MyErrorAttributes
 * @Description MyErrorAttributes
 * @Author Administrator
 * @Date 2018/12/14 15:39
 * @Version 1.0
 **/
//给容器中加入我们自定义的ErrorAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext",0);
        map.put("companly","binzikeji");
        map.put("ext",ext);
        return map;
    }
}
