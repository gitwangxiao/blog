package com.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.NotFoundException;
import com.wx.pojo.Blog;
import com.wx.pojo.Comment;
import com.wx.pojo.Tag;
import com.wx.pojo.Type;
import com.wx.service.BlogService;
import com.wx.service.CommentService;
import com.wx.service.TagService;
import com.wx.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.nio.file.Path;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;

    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }

//    @GetMapping("/{id}/{name}")
//    public String index(@PathVariable Integer id,@PathVariable String name){
//        int i= 9/0;
//        String blog=null;
//        if(blog==null){
//            throw new NotFoundException("博客找不到！");
//        }
//        System.out.println("--------index--------");
//        return "index";
//    }


    @GetMapping("/")
    public String getIndexBlog(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum,5);
        List<Blog> indexBlog = blogService.getIndexBlog();
        List<Type> indexType = typeService.getIndexType();
        List<Tag> indexTag = tagService.getIndexTag();

        PageInfo pageInfo= new PageInfo(indexBlog);
        List<Blog> recommendBlog = blogService.getRecommendBlog(8);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",indexType);
        model.addAttribute("tags",indexTag);
        model.addAttribute("recommendBlog",recommendBlog);
//        for (Blog blog : recommendBlog) {
//            System.out.println(blog.toString());
//        }
//        setTypeAndTag(model);
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String toBlog(@PathVariable Long id , Model model){
        Blog detailedBlog = blogService.getDetailedBlog(id);
//        System.out.println("标签=======================");
//        System.out.println(detailedBlog.getTags());
//        System.out.println(detailedBlog.toString());
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("blog",detailedBlog);
        return "blog";
    }

    @PostMapping("/search")
    public String toSearch(@RequestParam(required = false, defaultValue = "1",value="pagenum")int pagenum,
                           @RequestParam String query,Model model){

        PageHelper.startPage(pagenum,5);
        List<Blog> searchedBlog = blogService.getSearchBlog(query);
        PageInfo pageInfo = new PageInfo(searchedBlog);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/about")
    public String toAbout(){
        return "about";
    }



}
