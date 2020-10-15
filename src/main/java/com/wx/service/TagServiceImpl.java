package com.wx.service;


import com.wx.dao.TagDao;
import com.wx.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/14 15:18
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;


    @Transactional //事务注解
    @Override
    public int saveTag(Tag Tag) {
        return tagDao.saveTag(Tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDao.getTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    @Override
    public List<Tag> getIndexTag() {
        return tagDao.getIndexTag();
    }

    @Transactional
    @Override
    public int updateTag(Tag Tag) {
        return tagDao.updateTag(Tag);
    }
    @Transactional
    @Override
    public int deleteTag(Long id) {
        return tagDao.deleteTag(id);
    }


    @Override
    public List<Tag> getTagByString(String text) {    //从tagIds字符串中获取id，根据id获取tag集合
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long long1 : longs) {
            tags.add(tagDao.getTag(long1));
        }
        return tags;
    }

    private List<Long> convertToList(String ids) {  //把前端的tagIds字符串转换为list集合
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

}
