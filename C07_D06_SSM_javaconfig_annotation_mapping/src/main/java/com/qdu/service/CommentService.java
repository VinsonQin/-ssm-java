package com.qdu.service;

import com.qdu.entity.Comment;
import com.qdu.entity.CommentWithUser;

import java.util.List;

public interface CommentService{

//    List<Comment> getCommentsByPostId(Integer postId);
    List<CommentWithUser> getCommentsByPostId(Integer postId);
    void addComment(Comment comment);
    Integer countComment();
}
