package com.wx.service;

import com.wx.pojo.Blog;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author wx
 * @Description
 * @date 2020/8/15 17:00
 */
public interface BlogService {

    Blog getBlog(Long id);

    List<Blog> getBlogList();

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    List<Blog> searchBlogList(Blog blog);

    List<Blog> getIndexBlog();

    List<Blog> getRecommendBlog(int size);

    Blog getDetailedBlog(Long id);

    List<Blog> getSearchBlog(String query);

    List<Blog> getBlogByTypeId(Long id);

    List<Blog> getBlogByTagId(Long id);

    Map<String,List<Blog>> archiveBlog();  //归档博客

    int countBlog();  //查询博客条数

}
