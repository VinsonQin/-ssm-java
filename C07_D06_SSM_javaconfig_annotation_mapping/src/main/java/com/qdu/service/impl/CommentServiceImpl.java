package com.qdu.service.impl;

import com.qdu.entity.Comment;
import com.qdu.entity.CommentWithUser;
import com.qdu.entity.User;
import com.qdu.mapper.CommentMapper;
import com.qdu.mapper.UserMapper;
import com.qdu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    // 根据帖子ID获取评论列表
//    public List<Comment> getCommentsByPostId(Integer postId) {
//        return commentMapper.selectByPostId(postId);
//    }
    // 根据帖子ID获取评论列表
    public List<CommentWithUser> getCommentsByPostId(Integer postId) {
        List<Comment> comments = commentMapper.selectByPostId(postId);
        List<CommentWithUser> commentWithUsers = new ArrayList<>();

        for (Comment comment : comments) {
            User user = userMapper.selectByPrimaryKey(comment.getCuid());  // 查询评论者的用户信息
            commentWithUsers.add(new CommentWithUser(comment, user));  // 将评论和用户信息封装
        }

        return commentWithUsers;
    }

    // 添加评论
    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }

    public Integer countComment(){
        return commentMapper.countComment();
    }
}
