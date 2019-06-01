package com.Aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/*
*
 * @Date 2018/11/28   13:42

*/

@Aspect
@Component
public class TestAop {

    //AOP拦截
    @Pointcut("execution(* com.contaat.sys.controller.*.*(..))")
    public void PointController(){

    }
    @Before("PointController()")
    public void doBefore(){

    }
    @After("PointController()")
    public void doAfter(){

        System.out.println("后置通知,包括异常");
    }
    @Around("PointController()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Around：执行目标方法之前...");
        return point.proceed();
    }
}
