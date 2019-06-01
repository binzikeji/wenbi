package com.contaat.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.config.Common.utils.StringUtil;
import com.contaat.sys.entity.Kehu;
import com.contaat.sys.service.KehuService;
import com.contaat.sys.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@RequestMapping("{stopped}/admin/sys/kehu")
public class KehuController{
        @Autowired
        private KehuService kehuService;
        @Autowired
        private UserService userService;
        @GetMapping(value = "/kehufindlist")
        public String kehulist(){
            return "adminview/kehuview/List";
        }
        /**
         * 获取用户列表
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
                QueryWrapper wrapper = new QueryWrapper<Kehu>();
            }
            QueryWrapper<Kehu> wrapper = new QueryWrapper<>();
            if(searchText != null){
                searchText=searchText.replaceAll(" ", "");
                if(searchText.length() > 0){
                    wrapper.lambda().like(Kehu::getPhone,searchText);
                }
            }
            wrapper.lambda().notLike(Kehu::getStatus,"admin");
            IPage<Kehu> kehuPage= kehuService.page(page,wrapper);
            result.put("total", kehuPage.getTotal());
            result.put("rows", kehuPage.getRecords());
            return result;
        }
        //停封
        @ResponseBody
        @PostMapping(value = "/bancircle")
        public String bancircle(String id){
            return circle(id,"0");
        }
        //解封
        @ResponseBody
        @PostMapping(value = "/okcircle")
        public String okcircle(String id){
            return circle(id,"1");
        }
        //执行器
        public String circle(String id,String status){
            Kehu kehu = new Kehu();
            kehu.setStatus(status);
            kehu.setId(id);
            Boolean br = kehuService.updateById(kehu);
            if(!br){
                return "false";
            }
            return "true";
        }
}
