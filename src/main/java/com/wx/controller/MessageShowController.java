package com.wx.controller;

import com.wx.pojo.Message;
import com.wx.pojo.User;
import com.wx.service.CommentService;
import com.wx.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/27 12:08
 */
@Controller
public class MessageShowController {

    @Autowired
    private MessageService messageService;

    @Value("${message.avatar}")
    private String avatar;


    @GetMapping("/message")
    public String message() {
        return "message";
    }

//    查询留言列表
    @GetMapping("/messages")
    public String messages(Model model){
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages",messages);
        return "message :: messageList";
    }

//    新增留言
    @PostMapping("/message")
    public String saveMessage(Message message, HttpSession session, Model model){
        User user = (User)session.getAttribute("user");
        if(user!=null){
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        }else{
            message.setAvatar(avatar);
        }
        if(message.getParentMessage().getId()!=null){
            message.setParentMessageId(message.getParentMessage().getId());
        }
        messageService.saveMessage(message);
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages",messages);
        return "message :: messageList";

    }

    @GetMapping("/messages/{id}/delete")
    public String deleteMessage(@PathVariable Long id, Model model){
        messageService.deleteMessage(id);
        return "redirect:/message";
    }





}
