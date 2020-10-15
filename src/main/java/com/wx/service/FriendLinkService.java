package com.wx.service;

import com.wx.pojo.FriendLink;

import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/27 19:27
 */
public interface FriendLinkService {

    List<FriendLink> getFriendsList();

    FriendLink getFriendLink(Long id);

    int saveFriendLink(FriendLink friendLink);

    int updateFriendLink(FriendLink friendLink);

    int deleteFriendLink(Long id);

}
