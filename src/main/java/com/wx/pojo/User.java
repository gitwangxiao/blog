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
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Date createTime;
    private Date updateTime;
}
