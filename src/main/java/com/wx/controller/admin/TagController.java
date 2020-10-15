package com.wx.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.pojo.Tag;
import com.wx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/14 16:09
 */

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService TagService;

    //实现分页查询分类
    @GetMapping("/tags")
    public String Tags(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum, 10);
        List<Tag> allTag = TagService.getAllTag();
        //得到分页结果对象
        PageInfo<Tag> pageInfo = new PageInfo<>(allTag);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }
    //到增加分类页面
    @GetMapping("/tags/input")
    public String toAddTag(Model model){
        //返回一个Tag对象给前端th:object
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }
    //到编辑分类页面
    @GetMapping("/tags/{id}/input")
    public String toEditTag(@PathVariable Long id,Model model){
        model.addAttribute("tag",TagService.getTag(id));
        return "admin/tags-input";
    }

    //增加分类页面
    @PostMapping("/tags")
    public String addTag(Tag Tag, RedirectAttributes attributes){
        Tag Tag1 = TagService.getTagByName(Tag.getName());
        if (Tag1 != null) {
            attributes.addFlashAttribute("msg", "不能添加重复的分类");
            return "redirect:/admin/tags/input";
        }
        int t = TagService.saveTag(Tag);
        if (t == 0 ) {
            attributes.addFlashAttribute("msg", "添加失败");
        } else {
            attributes.addFlashAttribute("msg", "添加成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String editTag(@PathVariable Long id, Tag Tag,RedirectAttributes attributes){
        Tag Tag1 = TagService.getTagByName(Tag.getName());
        if (Tag1 != null) {
            attributes.addFlashAttribute("msg", "不能修改为重复的分类");
            return "redirect:/admin/tags/{id}/input";
        }
        int t = TagService.updateTag(Tag);
        if (t == 0 ) {
            attributes.addFlashAttribute("msg", "编辑失败");
        } else {
            attributes.addFlashAttribute("msg", "编辑成功");
        }
        return "redirect:/admin/tags";
    }


    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        TagService.deleteTag(id);
        attributes.addFlashAttribute("msg","删除成功！");
        return "redirect:/admin/tags";
    }

}
