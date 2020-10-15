package com.wx.service;

import com.wx.dao.BlogDao;
import com.wx.dao.CommentDao;
import com.wx.dao.MessageDao;
import com.wx.pojo.Comment;
import com.wx.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    //存放迭代找出的所有子代的集合
    private List<Message> tempReplys = new ArrayList<>();

    @Override
    public List<Message> listMessage() {
        //查询出父节点
        List<Message> messages = messageDao.findByBlogIdParentIdNull(Long.parseLong("-1"));
        for(Message message : messages){
            Long id = message.getId();
            String parentNickname1 = message.getNickname();
            List<Message> childMessages = messageDao.findByBlogIdParentIdNotNull(id);
//            查询出子评论
            combineChildren(childMessages, parentNickname1);
            message.setReplyMessages(tempReplys);
            tempReplys = new ArrayList<>();
        }
//        for (Message message : messages) {
//            System.out.println(message);
//        }
        return messages;

    }

    private void combineChildren(List<Message> childMessages, String parentNickname1) {
//        判断是否有一级子评论
        if(childMessages.size() > 0){
//                循环找出子评论的id
            for(Message childMessage : childMessages){
                String parentNickname = childMessage.getNickname();
                childMessage.setParentNickname(parentNickname1);
                tempReplys.add(childMessage);
                Long childId = childMessage.getId();
//                    查询出子二级评论
                recursively(childId, parentNickname);
            }
        }
    }

    private void recursively(Long childId, String parentNickname1) {
//        根据子一级评论的id找到子二级评论
        List<Message> replayMessages = messageDao.findByBlogIdAndReplayId(childId);

        if(replayMessages.size() > 0){
            for(Message replayMessage : replayMessages){
                String parentNickname = replayMessage.getNickname();
                replayMessage.setParentNickname(parentNickname1);
                Long replayId = replayMessage.getId();
                tempReplys.add(replayMessage);
                recursively(replayId,parentNickname);
            }
        }
    }

//    新增评论
    @Override
    public int saveMessage(Message message) {
        message.setCreateTime(new Date());
        int messages =messageDao.saveMessage(message);
//        文章评论计数
//        messageDao.getMessageCount();
        return messages;
    }

    @Override
    public List<Message> getChildMessage(Long id) {
        return null;
    }

    //    删除评论
    @Override
    public void deleteMessage(Long id) {
        messageDao.deleteMessage(id);
//        messageDao.getMessageCount();
    }
}