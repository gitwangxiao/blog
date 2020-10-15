package com.wx.service;

import com.wx.NotFoundException;
import com.wx.dao.BlogDao;
import com.wx.pojo.Blog;
import com.wx.pojo.BlogAndTag;
import com.wx.pojo.Tag;
import com.wx.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wx
 * @Description
 * @date 2020/8/15 21:44
 */

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;


    @Override   //后台编辑blog时通过id查找blog
    public Blog getBlog(Long id) {
        return blogDao.getBlog(id);
    }

    @Override   //后台展示blog列表
    public List<Blog> getBlogList() {
        return blogDao.getBlogList();
    }


    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }


    @Override
    public List<Blog> searchBlogList(Blog blog) {
        return blogDao.searchBlogList(blog);
    }


    @Override    //新增博客
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //保存博客
        blogDao.saveBlog(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            //新增时无法获取自增的id,在mybatis里修改
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return 1;
    }

    @Override   //编辑博客
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        blogDao.deleteBlogAndTag(blog.getId());
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return blogDao.updateBlog(blog);
    }


    public List<Blog> getIndexBlog(){
        return blogDao.getIndexBlog();
    }


    public List<Blog> getRecommendBlog(int size){
        return blogDao.getRecommendBlog(size);
    }

    @Override
    public Blog getDetailedBlog(Long id) {
        Blog detailedBlog = blogDao.getDetailedBlog(id);
        System.out.println(detailedBlog.getId());
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html

        //文章访问数量自增
        blogDao.updateViews(id);
        //文章评论数量更新
        blogDao.getCommentCountById(id);
        return detailedBlog;

    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }


    public List<Blog> getBlogByTypeId(Long id){
        return blogDao.getBlogByTypeId(id);
    }

    @Override
    public List<Blog> getBlogByTagId(Long id) {
        return blogDao.getBlogByTagId(id);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();
        Set<String> set = new LinkedHashSet<>(years);  //set去掉重复的年份

        //这里的LinkedHashMap是为了记录值插入的顺序，而刚开始用的hashMap不记录插入时的顺序，导致前端取出的顺序与预想的不一样
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : set) {
//            System.out.println(year);
            map.put(year, blogDao.findByYear(year));
        }
        return map;
    }


    @Override
    public int countBlog() {
        return blogDao.getIndexBlog().size();
    }


}
