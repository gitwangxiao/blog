package com.wx.dao;

import com.wx.pojo.Blog;
import com.wx.pojo.BlogAndTag;
import com.wx.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/15 17:36
 */
@Repository
@Mapper
public interface BlogDao {

    Blog getBlog(Long id);

    List<Blog> getBlogList();

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    List<Blog> searchBlogList(Blog blog);

    int saveBlogAndTag(BlogAndTag blogAndTag);
    int deleteBlogAndTag(Long id);

    List<Blog> getIndexBlog();

    List<Blog> getRecommendBlog(int size);

    Blog getDetailedBlog(Long id);

    int updateViews(Long id);

    void getCommentCountById(Long id);

    List<Blog> getSearchBlog(String query);

    List<Blog> getBlogByTypeId(Long id);

    List<Blog> getBlogByTagId(Long id);

    List<String> findGroupYear();  //查询所有年份，返回一个集合

    List<Blog> findByYear(@Param("year") String year);  //按年份查询博客
}
