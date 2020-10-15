package com.wx.service;

import com.wx.dao.FriendLinkDao;

import com.wx.pojo.FriendLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/27 19:28
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {
    @Autowired
    private FriendLinkDao friendLinkDao;

    public List<FriendLink> getFriendsList(){
        return friendLinkDao.getFriendsList();
    }

    @Override
    public FriendLink getFriendLink(Long id) {
        return friendLinkDao.getFriendLink(id);
    }

    @Override
    public int saveFriendLink(FriendLink friendLink) {
        friendLink.setCreateTime(new Date());
        return friendLinkDao.saveFriendLink(friendLink);
    }

    public int updateFriendLink(FriendLink friendLink){
        return friendLinkDao.updateFriendLink(friendLink);
    }

    @Override
    public int deleteFriendLink(Long id) {
        return friendLinkDao.deleteFriendLink(id);
    }
}
