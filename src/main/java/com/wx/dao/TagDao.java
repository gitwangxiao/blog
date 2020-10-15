package com.wx.dao;

import com.wx.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/14 15:19
 */

@Repository
@Mapper
public interface TagDao {

    int saveTag(Tag Tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

    List<Tag> getIndexTag();  //首页右侧展示Tag对应的博客数量

    int updateTag(Tag Tag);

    int deleteTag(Long id);
}
