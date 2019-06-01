package com.contaat.sys.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.contaat.sys.entity.Notice;
import com.contaat.sys.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName HelloController
 * @Description HelloController
 * @Author Administrator
 * @Date 2018/12/10 17:14
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "${started}")
public class HelloController {
    @Autowired
    private NoticeService noticeService;
    /**
     * 加载主页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index/index";
    }

    /**
     * 加载主页
     * @return
     */
    @GetMapping("/unAuth")
    public String unAuth(){
        return "index/unAuth";
    }
    /**
     * 加载登录页
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "index/login";
    }
    @GetMapping("/admin/login")
    public String adminlogin(){
        return "index/login";
    }
    /**
     * 加载注册页
     * @return
     */
    @GetMapping("/zhuce")
    public String zhuce(){
        return "index/zhuce";
    }
    /**
     * 加载详情页
     * @return
     */
    @GetMapping("/xiangqing")
    public String xiangqing(){
        return "index/xiangqing";
    }

    /**
     * 加载简介页
     * @return
     */
    @GetMapping("/jianjie")
    public String jianjie(){
        return "index/jianjie";
    }

    /**
     * 加载更多信息页
     * @return
     */
    @GetMapping("/gengduoxinxi")
    public String gengduoxinxi(){
        return "index/gengduoxinxi";
    }

    @GetMapping("/noticetext")
    @ResponseBody
    public Map noticetext(int pageNumber, int pageSize){
        Map<String,Object> result = new HashMap<String,Object>();
        Page page = new Page(pageNumber,pageSize);
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Notice::getStatus,"1");
        IPage<Notice> kehuPage= noticeService.page(page,wrapper);
        result.put("total", kehuPage.getTotal());
        result.put("rows", kehuPage.getRecords());
        return result;
    }
}
