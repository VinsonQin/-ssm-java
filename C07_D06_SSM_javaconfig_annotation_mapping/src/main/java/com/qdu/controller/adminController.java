package com.qdu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdu.entity.Post;
import com.qdu.entity.User;
import com.qdu.service.CommentService;
import com.qdu.service.PostService;
import com.qdu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/adminIndex")
    public String dashboard(Model model) {
        model.addAttribute("postCount", postService.countPost());
        model.addAttribute("commentCount", commentService.countComment());
        model.addAttribute("userCount", userService.countUser());
        return "adminIndex"; // 渲染管理端首页
    }

//    @GetMapping("/adminPost")
//    public String managePosts(Model model) {
//        List<Post> posts = postService.getAllPosts();
//        model.addAttribute("posts", posts);
//        return "adminPost"; // 帖子管理页面
//    }

    @GetMapping("/adminPost")
    public String getAllPosts(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            Model model) {
        // 获取分页后的帖子列表
        PageInfo<Post> pageInfo = postService.getAllPostsWithPagination(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "adminPost"; // 返回管理帖子页面
    }



    @PostMapping("/adminPost/delete")
    @ResponseBody
    public String deletePost(@RequestParam Integer postId) {
        postService.deletePostById(postId);
        return "success";
    }

    @GetMapping("/adminUser")
    public String manageUsers(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "adminUser"; // 用户管理页面
    }

    @PostMapping("/adminUser/delete")
    @ResponseBody
    public String deleteUser(@RequestParam Integer userId) {
        System.out.println("接收到的 userId: " + userId);
        userService.deleteUser(userId);
        return "success";
    }
}
