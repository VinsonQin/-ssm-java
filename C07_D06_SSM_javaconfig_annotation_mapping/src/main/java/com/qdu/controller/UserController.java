package com.qdu.controller;

import com.qdu.entity.User;
import com.qdu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/user",produces = {"text/html;charset=UTF-8"})
public class UserController {

    @Autowired
    private UserService userService;

    // 跳转到注册页面
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // 注册处理
    @PostMapping("/register")
    public String register(User user, Model model) {
        System.out.println("User register method called: " + user);
        boolean success = userService.register(user);
        if (success) {
            return "login"; // 注册成功跳转到登录页面
        } else {
            model.addAttribute("error", "邮箱已被注册！");
            return "register"; // 返回注册页面并提示错误
        }
    }

    // 跳转到登录页面
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // 前端页面路径为 templates/login.html
    }

    // 密码登录处理
    @PostMapping("/loginByPassword")
    public String loginByPassword(@RequestParam String email, @RequestParam String password,
                                  HttpServletRequest request, Model model) {
        User user = userService.loginByPassword(email, password);
        if (user != null) {
            // 登录成功，将用户信息存入 Session
            request.getSession().setAttribute("currentUser", user);
            return "menu"; // 跳转到主页
        } else {
            // 登录失败，返回错误信息
            model.addAttribute("error", "用户名或密码错误！");
            return "login";
        }
    }

    // 邮箱验证码登录处理
    @PostMapping("/loginByEmail")
    public String loginByEmail(@RequestParam String email, @RequestParam String code,
                               HttpServletRequest request, Model model) {
        User user = userService.loginByEmail(email, code);
        if (user != null) {
            // 登录成功，将用户信息存入 Session
            request.getSession().setAttribute("currentUser", user);
            return "menu";
        } else {
            // 登录失败，返回错误信息
            model.addAttribute("error", "验证码错误或已过期！");
            return "login";
        }
    }

    // 用户登出处理
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 清空 Session
        request.getSession().invalidate();
        return "redirect:/login";
    }

    // 发送邮箱验证码
    @PostMapping("/sendCode")
    @ResponseBody
    public String sendEmailCode(@RequestParam String email) {
        boolean sent = userService.sendEmailCode(email);
        return sent ? "验证码已发送，请查收！" : "验证码发送失败，请稍后重试！";
    }

//    @PostMapping("/uploadAvatar")
////    @ResponseBody
//    public String uploadAvatar(@RequestParam("avatar") MultipartFile avatar, HttpSession session,Model model) {
//        if (avatar.isEmpty()) {
//            return "请选择文件！";
//        }
//
//        // 从 Session 中获取用户 ID
//        User currentUser = (User) session.getAttribute("currentUser");
//        Integer uid = currentUser.getUid();
//        System.out.println("用户ID：" + uid);
//
//        try {
//            // 保存文件
//            String avatarPath = saveFile(avatar, "user/user" + uid);
//
//            // 调用 Service 更新数据库
//            boolean isUpdated = userService.updateAvatar(uid, avatarPath);
//
//            if (isUpdated) {
//                session.setAttribute("currentUser", currentUser); // 更新 session 中的用户信息
//                model.addAttribute("message", "信息修改成功！");
//            } else {
//                model.addAttribute("error", "信息修改失败，请重试！");
//            }
//
////            return isUpdated ? "头像上传成功" : "头像上传失败";
//            return "redirect:/post/user";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "头像上传失败：" + e.getMessage();
//        }
//    }

    @PostMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile avatar, HttpSession session, Model model) {
        if (avatar.isEmpty()) {
            model.addAttribute("error", "请选择文件！");
            return "userProfile"; // 假设你有一个用户信息视图
        }

        // 从 Session 中获取用户 ID
        User currentUser = (User) session.getAttribute("currentUser");
        Integer uid = currentUser.getUid();
        System.out.println("用户ID：" + uid);

        try {
            // 保存文件
            String avatarPath = saveFile(avatar, "user/user" + uid);

            // 调用 Service 更新数据库
            boolean isUpdated = userService.updateAvatar(uid, avatarPath);

            if (isUpdated) {
                // 更新 Session 中的用户信息
                currentUser.setUimg(avatarPath); // 假设 User 类有 `setAvatar` 方法
                session.setAttribute("currentUser", currentUser);

                // 添加成功提示
                model.addAttribute("message", "信息修改成功！");
            } else {
                model.addAttribute("error", "信息修改失败，请重试！");
            }

            // 重定向到帖子页面
            return "redirect:/post/user";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "头像上传失败：" + e.getMessage());
            return "userProfile"; // 返回一个错误页面或用户信息页面
        }
    }



    // 保存文件的通用方法
    private String saveFile(MultipartFile file, String uploadDir) throws IOException {
        // 获取文件名
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + originalFilename;

        // 定义文件存储路径
//        String basePath = System.getProperty("user.dir");
//        String path = basePath + "/" + uploadDir + "/" + fileName;
        String path = "C:/uploads/" + uploadDir + "/" + fileName;//实际本地路径
        String dataPath = "/uploads/" + uploadDir + "/" + fileName;//数据库存储路径
        System.out.println("文件保存路径：" + path);
        // 创建目标文件对象
        File destFile = new File(path);

        // 确保目标目录存在
        if (!destFile.getParentFile().exists()) {
            System.out.println("目标目录不存在");
            destFile.getParentFile().mkdirs();
        }

        // 保存文件
        file.transferTo(destFile);

        return dataPath; // 返回数据库存储的路径内容

    }

    // 修改用户信息
    @PostMapping("/update")
    public String updateUser(User user, HttpSession session, Model model) {
        // 检查当前登录用户
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || !currentUser.getUid().equals(user.getUid())) {
            model.addAttribute("error", "用户未登录或无权限修改！");
            return "error"; // 跳转到错误页面
        }

        // 仅更新提交的非空字段
        if (user.getUname() == null || user.getUname().isEmpty()) {
            user.setUname(currentUser.getUname());
        }
        if (user.getUinfo() == null || user.getUinfo().isEmpty()) {
            user.setUinfo(currentUser.getUinfo());
        }
        if (user.getUemail() == null || user.getUemail().isEmpty()) {
            user.setUemail(currentUser.getUemail());
        }
        if (user.getUimg() == null || user.getUimg().isEmpty()) {
            user.setUimg(currentUser.getUimg());
        }
        if (user.getUgender() == null || user.getUgender().isEmpty()) {
            user.setUgender(currentUser.getUgender());
        }
        if (user.getUpwd() == null || user.getUpwd().isEmpty()) {
            user.setUpwd(currentUser.getUpwd());
        }

        // 执行更新
        boolean success = userService.updateUser(user);
        if (success) {
            session.setAttribute("currentUser", user); // 更新 session 中的用户信息
            model.addAttribute("message", "信息修改成功！");
        } else {
            model.addAttribute("error", "信息修改失败，请重试！");
        }

        return "redirect:/post/user";
    }
}
