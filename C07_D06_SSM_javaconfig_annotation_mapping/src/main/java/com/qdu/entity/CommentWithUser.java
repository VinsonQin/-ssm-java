package com.qdu.entity;

public class CommentWithUser {

    private Comment comment;
    private User user;  // 存储用户信息

    // 构造函数
    public CommentWithUser(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }

    // Getters and Setters
    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
