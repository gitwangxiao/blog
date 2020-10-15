package com.wx.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.pojo.Blog;
import com.wx.pojo.Type;
import com.wx.service.BlogService;
import com.wx.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/26 16:13
 */

@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String toTypes(@PathVariable Long id, @RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum, Model model) {
        PageHelper.startPage(pagenum, 1000);
        List<Type> types = typeService.getIndexType();
        if (id == -1) {
            id = types.get(0).getId();
        }
        List<Blog> blogs = blogService.getBlogByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",types);
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
