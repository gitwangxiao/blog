package com.wx.dao;

import com.wx.pojo.Type;
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
public interface TypeDao {

    int saveType(Type type);

    Type getType(Long id);//通过id获取type

    Type getTypeByName(String name);//通过name过去type

    List<Type> getAllType(); //获取多有的type

    List<Type> getIndexType(); //首页右侧展示type对应的博客数量

    int updateType(Type type); //更新type

    int deleteType(Long id); //删除type
}
