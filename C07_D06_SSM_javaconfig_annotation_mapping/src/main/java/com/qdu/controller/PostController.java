package com.qdu.controller;

import com.qdu.entity.Post;
import com.qdu.entity.User;
import com.qdu.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
//@ResponseBody
//@RequestMapping(value = "/post",produces = "application/json")
@RequestMapping(value = "/post",produces = "text/html")
public class PostController {

    @Autowired
    private PostService postService;

    // 跳转到发布页面
    @GetMapping("/addPost")
    public String addPostPage() {
        return "addPost";
    }

    @PostMapping(value = "/addPost",produces = {"text/html;charset=UTF-8"})
//    @ResponseBody
    public String addPost(
            @RequestParam("puid") Integer puid,
            @RequestParam("pname") String pname,
            @RequestParam("pinfo") String pinfo,
            @RequestParam("images") MultipartFile[] images) {
        try {
            // 保存帖子信息
            Post post = new Post();
            post.setPuid(puid);
            post.setPname(pname);
            post.setPinfo(pinfo);

            // 保存帖子到数据库并获取帖子ID
            Integer postId = postService.addPost(post);
            System.out.println("帖子ID"+postId);
            if (postId == null) {
                throw new IllegalStateException("帖子插入失败，未生成主键值！");
            }

            // 保存图片并更新帖子图片路径
            if (images != null && images.length > 0) {
                List<String> imagePaths = new ArrayList<>();
                for (MultipartFile image : images) {
                    if (!image.isEmpty()) {
                        String imagePath = saveFile(image, "posts/post" + postId);
                        imagePaths.add(imagePath);
                    }
                }

                // 用分号拼接图片路径并更新数据库
                String combinedPaths = String.join(";", imagePaths);
                postService.uploadPostImages(postId, combinedPaths);
            }

            return "redirect:/post/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "帖子发布失败：" + e.getMessage();
        }
    }

    // 保存文件的通用方法
    private String saveFile(MultipartFile file, String uploadDir) throws IOException {
        // 获取文件名
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + originalFilename;
        // 定义文件存储路径
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

    @GetMapping({"/search","/index"})
    public String searchPosts(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Post> posts;

        // 如果没有关键词，查询所有帖子；否则，查询匹配的帖子
        if (keyword == null || keyword.trim().isEmpty()) {
            posts = postService.getAllPosts();
        } else {
            posts = postService.searchPosts(keyword);
        }

        // 将帖子列表存入 model 中供 Thymeleaf 渲染
        model.addAttribute("posts", posts);
        model.addAttribute("keyword", keyword); // 保留搜索框中的关键词

        // 返回帖子主页
        return "index";
    }

    @GetMapping("/user")
    public String listPosts(HttpSession session, Model model) {
        List<Post> posts;
        // 从 session 中获取当前用户的 uid
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            // 用户未登录，跳转到登录页面
            return "redirect:/login";
        }
        Integer uid = currentUser.getUid();
        posts = postService.listMyPost(uid);
        model.addAttribute("myPosts",posts);
        return "user";
    }

    // 删除帖子
    @PostMapping(value = "/delete",produces = "application/json")
    @ResponseBody
    public Map<String, Object> deletePost(@RequestParam("pid") Integer pid) {
        Map<String, Object> response = new HashMap<>();
        // 日志：打印收到的请求
        System.out.println("【日志】收到删除请求，pid=" + pid);
        boolean success = postService.deletePostById(pid);
        System.out.println("【日志】调用 postService.deletePostById(pid=" + pid + ") 的结果：" + success);
        if (success) {
            response.put("success", true);
            response.put("message", "删除成功！");
        } else {
            response.put("success", false);
            response.put("message", "删除失败，请重试！");
        }
        // 日志：返回给前端的响应
        System.out.println("【日志】返回的响应：" + response);
        return response;
    }

}
