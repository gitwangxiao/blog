package com.wx.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.pojo.Blog;
import com.wx.pojo.User;
import com.wx.service.BlogService;
import com.wx.service.TagService;
import com.wx.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/14 0:12
 */

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }


    @GetMapping("/blogs")//后台博客列表展示
    public String blogs(@RequestParam(defaultValue = "1", value = "pagenum") int pagenum, Model model) {
        //按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pagenum, 10, orderBy);
        List<Blog> list = blogService.getBlogList();//这里已经分页了，list里面只有五条数据
//        for (Blog blog : list) {
//            System.out.println(blog.toString());
//        }
        //将查询结果放进PageInfo里，他会包裹上pageNum，pageSize，StartRow，EndRow，prePage，isFirstPage，hasPrePage等信息
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")   //后台搜索博客列表
    public String searchBlogList(@RequestParam(defaultValue = "1", value = "pagenum")Integer pagenum, Blog blog, Model model) {
        PageHelper.startPage(pagenum, 10);

        List<Blog> list = blogService.searchBlogList(blog);
//        for (Blog blog1 : list) {
//            System.out.println(blog1.toString());
//        }
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", "查询成功");
        setTypeAndTag(model);
        return "admin/blogs :: blogList";

    }

    //跳转到新增blog页面
    @GetMapping("/blogs/input")
    public String toAddBlog(Model model) {
        model.addAttribute("blog", new Blog());
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/input") //去编辑博客页面
    public String toEditBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlog(id);
//        blog.init();   //将tags集合转换为tagIds字符串
        model.addAttribute("blog", blog);    //返回一个blog对象给前端th:object
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    //blog新增提交
    @PostMapping("/blogs")
    public String addBlog(Blog blog, RedirectAttributes attributes, HttpSession session) {
        //从session中获取user
        blog.setUser((User) session.getAttribute("user"));
        //再从user中获取userId
        blog.setUserId(blog.getUser().getId());

        //从type中获取typeId
//        blog.setTypeId(blog.getType().getId());

        //给blog中的List<Tag>赋值
        blog.setTags(tagService.getTagByString(blog.getTagIds()));

        if (blog.getId() == null) {   //id为空，则为新增
            blogService.saveBlog(blog);
        } else {
            blogService.updateBlog(blog);
        }
        attributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/admin/blogs";
    }

    //删除博客
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlogs(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/blogs";
    }


}
