package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
private final Logger logger = LoggerFactory.getLogger(${table.controllerName}.class);

@Autowired
public ${table.serviceName} i${entity}Service;

/**
 * 跳转列表页面
 * @param request
 * @param model
 * @return
 */
@RequestMapping(method= RequestMethod.GET,value = {"/${table.entityPath}Index"})
public String index(HttpServletRequest request, Model model) {
        return "${table.entityPath}ListIndex";
        }

/**
 * 分页查询数据
 *
 * @param page  分页信息
 * @param ${table.entityPath}   查询条件
 * @return
 */
@ResponseBody
@GetMapping("/get${entity}PageList")
public IPage<${entity}> get${entity}List(Page<${entity}> page,${entity} ${table.entityPath}) {
        IPage<${entity}> result = i${entity}Service.page(page,new QueryWrapper<${entity}>());
        return result;
}

/**
 * 跳转添加页面
 * @param request
 * @param response
 * @param model
 * @return
 */
@RequestMapping(method=RequestMethod.GET,value="/${table.entityPath}Add")
public String ${table.entityPath}Add(HttpServletRequest request,HttpServletResponse response,Model model) {
    try {


    }catch (Exception ex){
        logger.error("${table.entityPath}Add -=- {}",ex.toString());
    }
        return "${table.entityPath}Add";
}

/**
 * 跳转修改页面
 * @param request
 * @param id  实体ID
 * @return
 */
@RequestMapping(method=RequestMethod.GET,value="/${table.entityPath}Update")
public String ${table.entityPath}Update(HttpServletRequest request,Long id) {
        try {
          ${entity} ${table.entityPath} = i${entity}Service.getById(id);
        request.setAttribute("${table.entityPath}",${table.entityPath});
        }catch (Exception ex){
        logger.error("${table.entityPath}Update -=- {}",ex.toString());
        }
        return "${table.entityPath}Upd";
}

/**
 * 保存和修改公用的
 * @param ${table.entityPath}  传递的实体
 * @return  0 失败  1 成功
 */
@ResponseBody
@RequestMapping(method=RequestMethod.POST,value="/${table.entityPath}Save")
public int ${table.entityPath}Save(${entity} ${table.entityPath}) {
    int count = 0;
    try {
    count = i${entity}Service.saveOrUpdate(${table.entityPath}) ? 1 : 0;
    } catch (Exception e) {
    logger.error("${table.entityPath}Save -=- {}",e.toString());
    }
    return count;
}

/**
 * 根据id删除对象
 * @param id  实体ID
 * @return 0 失败  1 成功
 */
@ResponseBody
@RequestMapping(method= RequestMethod.POST,value="/${table.entityPath}Delete")
public int ${table.entityPath}Delete(Long id){
        int count = 0;
        try {
        count = i${entity}Service.removeById(id) ? 1 : 0;
        }catch (Exception e){
        logger.error("${table.entityPath}Delete -=- {}",e.toString());
        }
        return count;
        }

/**
 * 批量删除对象
 * @param item 实体集合ID
 * @return  0 失败  1 成功
 */
@ResponseBody
@RequestMapping(method= RequestMethod.POST,value="/${table.entityPath}Delete")
public int deleteBatchIds(String item){
        int count = 0;
        try {
             List<Long> ids = (List<Long>) JSON.parse(item);
             count = i${entity}Service.removeByIds(ids) ? 1 : 0;
        }catch (Exception e){
                logger.error("${table.entityPath}BatchDelete -=- {}",e.toString());
        }
        return count;
    }
}
