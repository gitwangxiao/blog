package com.wx.service;



import com.wx.pojo.Comment;
import com.wx.pojo.Message;

import java.util.List;

/**
 * @Description: 博客评论业务层接口
 * @Author: ONESTAR
 * @Date: Created in 13:26 2020/4/5
 * @QQ群: 530311074
 * @URL: https://onestar.newstar.net.cn/
 */
public interface MessageService {

    List<Message> listMessage();

    int saveMessage(Message message);

//    查询子评论
    List<Message> getChildMessage(Long id);

    //删除评论
    void deleteMessage(Long id);

}