package com.wx.service;

import com.wx.pojo.Type;

import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/14 14:45
 */
public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    List<Type> getAllType();

    List<Type> getIndexType();  //首页右侧展示type对应的博客数量

    int updateType(Type type);

    int deleteType(Long id);

}
