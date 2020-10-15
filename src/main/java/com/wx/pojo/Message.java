package com.wx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/13 12:43
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private Long parentMessageId;
    private boolean adminMessage;


    //回复评论
    private List<Message> replyMessages = new ArrayList<>();
    private Message parentMessage;
    private String parentNickname;


}
