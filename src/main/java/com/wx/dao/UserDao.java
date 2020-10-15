package com.wx.dao;

import com.wx.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wx
 * @Description
 * @date 2020/8/13 19:45
 */


@Mapper
@Repository
public interface UserDao {
    User queryByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
