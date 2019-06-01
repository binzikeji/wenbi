package com.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginHandlerInterceptor
 * @Description 登录检查
 * @Author Administrator
 * @Date 2018/12/12 11:10
 * @Version 1.0
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user == null){
            //未登录
            request.setAttribute("msg","没权限");
            request.getRequestDispatcher("/zhuye.html").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
