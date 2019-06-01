package com.contaat.sys.controller;



import com.contaat.sys.entity.Kehu;
import com.contaat.sys.service.KehuService;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.contaat.sys.service.WrTextService;
import com.contaat.sys.entity.WrText;

import java.util.*;

/**
 *
 * @author 胡胡道人
 * @since 2018-12-17
 */
@Controller
@RequestMapping("${stopped}")
public class WrTextController {

@Autowired
private WrTextService WrTextService;
@Autowired
private KehuService kehuService;

    /**
     * 查询列表取第一条数据
     * @return
     */
    @GetMapping("/WrTextList/{kehuid}")
    public String wrTextList(WrText wrText, Model model){
        wrText.setStatus("1");
        String kehuid = wrText.getKehuid();
        List<WrText> wrTextList = WrTextService.list(new QueryWrapper<WrText>().setEntity(wrText).orderByDesc("update_date"));
        if (wrTextList.size() > 0){
                model.addAttribute("wrTextList",wrTextList);
                wrText = wrTextList.get(0);
                model.addAttribute("wrText",wrText);
        }
        Kehu kehu = kehuService.getOne(new QueryWrapper<Kehu>().lambda().eq(Kehu::getId, kehuid));
        model.addAttribute("kehu",kehu);
        model.addAttribute("kehuid",wrText.getKehuid());
        model.addAttribute("actionUri",wrText.getId());
        model.addAttribute("huishouzhan","0");
        return "qianduan/zhuye";
    }

    /**
     * ajax请求加载文档
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/WrTextMain")
    public WrText wrTextMain (String id){
            WrText wrText = WrTextService.getOne(new QueryWrapper<WrText>().lambda().eq(WrText::getId,id));
            if(wrText != null){
                    return wrText;
            }else {
                    return null;
            }
    }

    /**
     * 新增文档
     * @param wrText
     * @return
     */
    @PostMapping("/WrTextXinzeng/{kehuid}")
    public String WrTextXinzeng(WrText wrText){
            wrText.setTextName("标 题");
            Date date = new Date();
            wrText.setCreateDate(date);
            wrText.setStatus("1");
            wrText.setUpdateDate(date);
            wrText.setText("<h4><strong>Hello Word!!</strong></h4>");
            WrTextService.save(wrText);
            return "redirect:/a/WrTextList/"+wrText.getKehuid();
    }

    /**
     * 删除文档
     * @param wrText
     * @return
     */
    @PostMapping("/WrTextShanchu/{id}")
    public String WrTextShanchu(WrText wrText){
            wrText = WrTextService.getOne(new QueryWrapper<WrText>().setEntity(wrText));
            wrText.setStatus("0");
            wrText.setUpdateDate(new Date());
            WrTextService.updateById(wrText);
            return "redirect:/a/WrTextList/"+wrText.getKehuid();
    }

    /**
     * 保存文档
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/WrTextBaocun")
    public Map<String,Object> WrTextBaocun(String id, String textName, String text, String kehuid){
        Map<String,Object> map = new HashMap<>();
        Date date = new Date();
        WrText wrText = WrTextService.getOne(new QueryWrapper<WrText>().lambda().eq(WrText::getId,id));
        if (wrText == null){
            wrText = new WrText();
            wrText.setTextName(textName);
            wrText.setCreateDate(date);
            wrText.setUpdateDate(date);
            wrText.setKehuid(kehuid);
            wrText.setStatus("1");
            wrText.setText(text);
            WrTextService.save(wrText);
            map.put("shifoushidiyitiaoshujv","1");
            map.put("date",date);
            return map;
        }else {
            wrText.setTextName(textName);
            wrText.setText(text);
            wrText.setUpdateDate(date);
            WrTextService.updateById(wrText);
            map.put("shifoushidiyitiaoshujv","0");
            map.put("date",date);
            return map;
        }
    }

    /**
     * 回收站
     * @return
     */
    @GetMapping("/huishouzhan/{kehuid}")
    public String huishouzhan(WrText wrText, Model model){
        wrText.setStatus("0");
        String kehuid = wrText.getKehuid();
        List<WrText> wrTextList = WrTextService.list(new QueryWrapper<WrText>().setEntity(wrText).orderByDesc("update_date"));
        if (wrTextList.size() > 0){
            model.addAttribute("wrTextList",wrTextList);
            wrText = wrTextList.get(0);
            model.addAttribute("wrText",wrText);
        }
        Kehu kehu = kehuService.getOne(new QueryWrapper<Kehu>().lambda().eq(Kehu::getId, kehuid));
        model.addAttribute("kehu",kehu);
        model.addAttribute("kehuid",wrText.getKehuid());
        model.addAttribute("actionUri",wrText.getId());
        model.addAttribute("huishouzhan","1");
        return "qianduan/zhuye";
    }

    @PostMapping("/chehui/{id}")
    public String chehui(WrText wrText){
        wrText = WrTextService.getOne(new QueryWrapper<WrText>().setEntity(wrText));
        if(wrText != null){
            wrText.setStatus("1");
            wrText.setUpdateDate(new Date());
            WrTextService.updateById(wrText);
        }
        return "redirect:/a/huishouzhan/"+wrText.getKehuid();
    }
}
