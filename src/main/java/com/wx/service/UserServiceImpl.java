package com.wx.service;

import com.wx.dao.UserDao;
import com.wx.pojo.User;
import com.wx.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wx
 * @Description
 * @date 2020/8/13 19:44
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.queryByUsernameAndPassword(username,MD5Utils.code(password));
        return user;
    }

}