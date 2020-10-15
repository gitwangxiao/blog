package com.wx.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.pojo.FriendLink;
import com.wx.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/28 17:42
 */

@Controller
@RequestMapping("/admin")
public class FriendLinkController {
    @Autowired
    private FriendLinkService friendLinkService;

    @GetMapping("/friends")
    public String toFriends(@RequestParam(required = false,defaultValue = "1",value="pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum,5);
        List<FriendLink> friendsList = friendLinkService.getFriendsList();

        PageInfo<FriendLink> pageInfo = new PageInfo<>(friendsList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/friendLink";
    }
    @GetMapping("/friends/input")
    public String toAddFriend(Model model){
        model.addAttribute("friendLink",new FriendLink());
        return "admin/friendLink-input";
    }

    @GetMapping("/friends/{id}/input")
    public String toEditFriend(@PathVariable long id,Model model){
        FriendLink friendLink = friendLinkService.getFriendLink(id);
        model.addAttribute("friendLink",friendLink);
        return "admin/friendLink-input";
    }


    @PostMapping("/friends")
    public String saveFriendLink(FriendLink friendLink,Model model){
        friendLinkService.saveFriendLink(friendLink);
        return "redirect:/admin/friends";
    }

    @PostMapping("/friends/{id}")
    public String editFriendLink(FriendLink friendLink,Model model){
        friendLinkService.updateFriendLink(friendLink);
        return "redirect:/admin/friends";
    }

    @GetMapping("/friends/{id}/delete")
    public String deleteFriendLink(@PathVariable long id){
        friendLinkService.deleteFriendLink(id);
        return "redirect:/admin/friends";
    }
}
