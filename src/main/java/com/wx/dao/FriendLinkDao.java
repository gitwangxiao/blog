package com.wx.dao;

import com.wx.pojo.FriendLink;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/27 19:22
 */

@Mapper
@Repository
public interface FriendLinkDao {

    List<FriendLink> getFriendsList();

    FriendLink getFriendLink(Long id);

    int saveFriendLink(FriendLink friendLink);

    int updateFriendLink(FriendLink friendLink);

    int deleteFriendLink(Long id);


}
