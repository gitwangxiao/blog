package com.wx.controller;

import com.wx.pojo.FriendLink;
import com.wx.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/27 19:20
 */

@Controller
public class FriendsController {
    @Autowired
    private FriendLinkService friendLinkService;

    @GetMapping("/friends")
    public String toFriends(Model model) {

        List<FriendLink> friendlinks = friendLinkService.getFriendsList();

        model.addAttribute("friendlinks", friendlinks);

        return "friends";
    }
}