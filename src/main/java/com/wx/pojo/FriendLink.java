package com.wx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wx
 * @Description
 * @date 2020/8/13 12:43
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendLink {

    private Long id;
    private String blogName;
    private String blogAddress;
    private String pictureAddress;
    private Date createTime;

}
