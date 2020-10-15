package com.wx.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.pojo.Type;
import com.wx.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/14 16:09
 */

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    //实现分页查询分类
    @GetMapping("/types")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum, 5);
        List<Type> allType = typeService.getAllType();
        //得到分页结果对象
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }
    //到增加分类页面
    @GetMapping("/types/input")
    public String toAddType(Model model){
        //返回一个type对象给前端th:object
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }
    //到编辑分类页面
    @GetMapping("/types/{id}/input")
    public String toEditType(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    //增加分类页面
    @PostMapping("/types")
    public String addType(@Valid Type type, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("msg", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t = typeService.saveType(type);
        if (t == 0 ) {
            attributes.addFlashAttribute("msg", "添加失败");
        } else {
            attributes.addFlashAttribute("msg", "添加成功");
        }
        return "redirect:/admin/types";
    }
    @PostMapping("/types/{id}")
    public String editType(@PathVariable Long id, Type type,RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("msg", "不能修改为重复的分类");
            return "redirect:/admin/types/{id}/input";
        }
        int t = typeService.updateType(type);
        if (t == 0 ) {
            attributes.addFlashAttribute("msg", "编辑失败");
        } else {
            attributes.addFlashAttribute("msg", "编辑成功");
        }
        return "redirect:/admin/types";
    }


    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("msg","删除成功！");
        return "redirect:/admin/types";
    }

}
