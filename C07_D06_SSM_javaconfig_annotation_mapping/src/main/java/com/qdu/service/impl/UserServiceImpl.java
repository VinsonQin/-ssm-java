package com.qdu.service.impl;

import com.qdu.entity.User;
import com.qdu.mapper.UserMapper;
import com.qdu.service.UserService;
import com.qdu.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 使用线程安全的 Map 存储验证码与过期时间
    private static final Map<String, String> emailCodeCache = new ConcurrentHashMap<>();
    private static final Map<String, Long> codeExpiryCache = new ConcurrentHashMap<>();


    @Override
    public boolean register(User user) {
        if (userMapper.selectAll().stream().anyMatch(u -> u.getUemail().equals(user.getUemail()))) {
            return false; // 邮箱已注册
        }
        return userMapper.insert(user) > 0;
    }

    @Override
    public User loginByPassword(String email, String password) {
        return userMapper.selectAll().stream()
                .filter(u -> u.getUemail().equals(email) && u.getUpwd().equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean sendEmailCode(String email) {
        // 生成验证码并发送邮件
        String code = EmailUtils.sendEmailCode(email);
        if (code != null) {
            // 将验证码与时间存储到缓存中
            emailCodeCache.put(email, code);
            codeExpiryCache.put(email, System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2));
            return true;
        }
        return false;
    }

    @Override
    public User loginByEmail(String email, String inputCode) {
        // 检查验证码是否存在
        String cachedCode = emailCodeCache.get(email);
        Long expiryTime = codeExpiryCache.get(email);

        // 验证码不存在或已过期
        if (cachedCode == null || expiryTime == null || System.currentTimeMillis() > expiryTime) {
            return null; // 验证码无效
        }

        // 验证用户输入的验证码是否正确
        if (cachedCode.equals(inputCode)) {
            // 验证通过，清除缓存
            emailCodeCache.remove(email);
            codeExpiryCache.remove(email);

            // 验证通过后，查询数据库中的用户信息
            User user = userMapper.selectByEmail(email);
            System.out.println(user);
            if (user == null) {
                // 如果数据库中没有用户记录，可以选择自动注册用户或返回 null
                User newUser = new User();
                newUser.setUemail(email);
                newUser.setUname("默认用户名"); // 可以根据需求修改默认用户名
                userMapper.insert(newUser);
                return newUser;
            }
            return user;
        }
        return null; // 验证码错误
    }

    public boolean updateAvatar(Integer uid, String avatarPath) {
        return userMapper.updateAvatar(uid, avatarPath) > 0;
    }

    // 更新用户信息
    public boolean updateUser(User user) {
        return userMapper.updateByPrimaryKey(user) > 0;
    }

    public Integer countUser(){
        return userMapper.countUser();
    }

    public List<User> getAllUser(){
        return userMapper.selectAll();
    }

    public boolean deleteUser(Integer uid) {
        return userMapper.deleteByPrimaryKey(uid) > 0;
    }
}
