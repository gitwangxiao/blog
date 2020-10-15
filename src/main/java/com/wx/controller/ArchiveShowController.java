package com.wx.controller;

import com.wx.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wx
 * @Description
 * @date 2020/8/26 23:33
 */

@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount", blogService.countBlog());
        return "archives";
    }

}
