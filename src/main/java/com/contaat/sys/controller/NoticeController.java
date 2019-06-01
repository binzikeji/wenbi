package com.contaat.sys.controller;



import com.config.Common.utils.StringUtil;
import org.springframework.stereotype.Controller;
import io.swagger.annotations.ApiOperation;
import org.junit.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.contaat.sys.service.NoticeService;
import com.contaat.sys.entity.Notice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 *
 * @author 胡胡道人
 * @since 2018-12-21
 */
@Controller
@RequestMapping("{stopped}/admin/sys/notice")
public class NoticeController {
@Autowired
public NoticeService noticeService;
/**
 * 跳转发布公告列表页面
 * @return
 */
@GetMapping(value = "/noticefindlist")
public String noticeList(){
        return "adminview/noticeview/List";
}

/**
 * 获取公告列表
 * @param pageNumber 当前页
 * @param pageSize 每页显示条数
 * @param searchText 搜索名称
 * @return
 */
@ResponseBody
@GetMapping(value = "/getList")
public Map<String, Object> getListPage(int pageNumber, int pageSize, String searchText){
        Map<String,Object> result = new HashMap<String,Object>();
        Page page = new Page(pageNumber,pageSize);
        if (StringUtil.isNotEmpty(searchText)) {
                QueryWrapper wrapper = new QueryWrapper<Notice>();
        }
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        if(searchText != null){
                searchText=searchText.replaceAll(" ", "");
                if(searchText.length() > 0){
                        wrapper.lambda().like(Notice::getTextName,searchText);
                }
        }
        IPage<Notice> noticePage= noticeService.page(page,wrapper);
        result.put("total", noticePage.getTotal());
        result.put("rows", noticePage.getRecords());
        return result;
}
//删除公告
@ResponseBody
@PostMapping(value = "/bancircle")
public String bancircle(String id){
        return circle(id,"0");
}
//恢复公告
@ResponseBody
@PostMapping(value = "/okcircle")
public String okcircle(String id){
        return circle(id,"1");
}
//执行器
public String circle(String id,String status){
        Notice notice = new Notice();
        notice.setStatus(status);
        notice.setId(id);
        Boolean br = noticeService.updateById(notice);
        if(!br){
                return "false";
        }
        return "true";
}
/**
 * 跳转修改页面
 * @param request
 * @param model
 * @return
 */

/**
 * 跳转添加页面
 * @return
 */
@GetMapping(value = "/noticeAdd")
public String noticeAdd(){
        return "adminview/noticeview/Add";
}
/*公告保存*/
/*@ResponseBody*/
@RequestMapping("/noticere")
public String noticeRe(Notice notice){
    Date date = new Date();
    notice.setLoginName("admin");
    notice.setCreateDate(date);
    notice.setStatus("0");//
    Boolean br = noticeService.saveOrUpdate(notice);
    return "adminview/noticeview/List";
}
}
