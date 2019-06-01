package com.contaat.sys.controller;



import org.springframework.stereotype.Controller;
import io.swagger.annotations.ApiOperation;
import org.junit.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.contaat.sys.service.RoleService;
import com.contaat.sys.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 胡胡道人
 * @since 2018-12-19
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {


@Autowired
public RoleService RoleService;

/**
 * 跳转列表页面
 * @param request
 * @param model
 * @return
 */
@RequestMapping(method= RequestMethod.GET,value = {"/roleIndex"})
public String index(HttpServletRequest request, Model model) {
        return "roleListIndex";
}

/**
 * 分页查询数据
 *
 * @param @param page<Tt></>  分页信息
 * @return
 */
@ApiOperation(value = "获取列表-分页",notes = "获取所有得信息-分页")
@ResponseBody
@GetMapping(value = "/Rolepagefind")
public IPage<Role> pageVo(Page<Role> page){
        //siez   当前页的N条数据
        //current 当前页
        IPage<Role> RoleIPage= RoleService.page(page,null);
        Assert.assertSame(page, RoleIPage);
        return RoleIPage;
}
/**
 * 跳转添加页面
 * @param request
 * @param response
 * @param model
 * @return
 */
@RequestMapping(method=RequestMethod.GET,value="/roleAdd")
public String roleAdd(HttpServletRequest request,HttpServletResponse response,Model model) {
        return "roleAdd";
}

/**
 * 跳转修改页面
 * @param id  实体ID
 * @return
 */
@RequestMapping(method=RequestMethod.GET,value="/roleUpdate")
public String roleUpdate(Model model,Long id) {
    Role role = RoleService.getById(id);
        model.addAttribute("role",role);
        return "roleAdd";
}

/**
 * 保存和修改公用的
 * @param role  传递的实体
 * @return  0 失败  1 成功
 */
@ResponseBody
@RequestMapping(method=RequestMethod.POST,value="/roleSave")
public int roleSave(Role role) {
        int count = 0;
        count = RoleService.saveOrUpdate(role) ? 1 : 0;
        return count;
}

/**
 * 根据id删除对象
 * @param id  实体ID
 * @return 0 失败  1 成功
 */
@ResponseBody
@RequestMapping(method= RequestMethod.POST,value="/roleDelete")
public int roleDelete(Long id){
        int count = 0;
        count = RoleService.removeById(id) ? 1 : 0;
        return count;
}

/**
 * 批量删除对象
 * @param item 实体集合ID
 * @return  0 失败  1 成功
 */
@ResponseBody
@RequestMapping(method= RequestMethod.POST,value="/roleBatchDelete")
public int deleteBatchIds(String item){
        int count = 0;
        List<Long> ids = (List<Long>) JSON.parse(item);
        count = RoleService.removeByIds(ids) ? 1 : 0;
        return count;
}
}
