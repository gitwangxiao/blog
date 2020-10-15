package com.wx.service;

import com.wx.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author wx
 * @Description
 * @date 2020/8/13 19:44
 */
@Service
public interface UserService {
    public User checkUser(String username, String password);
}
