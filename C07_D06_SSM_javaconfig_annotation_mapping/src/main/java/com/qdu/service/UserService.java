package com.qdu.service;

import com.qdu.entity.User;

import java.util.List;

public interface UserService {

    boolean register(User user);
    User loginByPassword(String username, String password);
    boolean sendEmailCode(String email);
    User loginByEmail(String email, String code);
    boolean updateAvatar(Integer userId, String avatarPath);
    boolean updateUser(User user);
    Integer countUser();
    List<User> getAllUser();
    boolean deleteUser(Integer userId);
}
