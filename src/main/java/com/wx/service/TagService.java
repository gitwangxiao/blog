package com.wx.service;

import com.wx.pojo.Tag;

import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/14 14:45
 */
public interface TagService {

    int saveTag(Tag Tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

    List<Tag> getIndexTag();  //首页右侧展示Tag对应的博客数量

    int updateTag(Tag Tag);

    int deleteTag(Long id);

    List<Tag> getTagByString(String text);
}
