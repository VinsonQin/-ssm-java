package com.qdu.controller;

import com.qdu.entity.CommentWithUser;
import com.qdu.entity.Post;
import com.qdu.entity.Comment;
import com.qdu.entity.PostWithUser;
import com.qdu.service.CommentService;
import com.qdu.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/postDetail")
public class PostDetailController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

//    @GetMapping("/{postId}")
//    public String getPostDetail(@PathVariable Integer postId, Model model) {
//        Post post = postService.getPostById(postId); // 根据帖子 ID 获取帖子详情
//        model.addAttribute("post", post);
//        List<CommentWithUser> comments = commentService.getCommentsByPostId(postId);// 获取该帖子的评论列表
//        model.addAttribute("comments", comments);
//        return "postDetail"; // 返回帖子详情页面
//    }
    @GetMapping("/{postId}")
    public String getPostDetail(@PathVariable Integer postId, Model model) {
        // 获取帖子和用户信息
        PostWithUser postWithUser = postService.getPostWithUserById(postId);
        model.addAttribute("postWithUser", postWithUser);

        // 获取评论列表
        List<CommentWithUser> comments = commentService.getCommentsByPostId(postId);
        model.addAttribute("comments", comments);

        return "postDetail"; // 返回帖子详情页面
    }


    // 提交评论
    @PostMapping("/comment")
    public String addComment(@RequestParam("cpid") Integer cpid,
                             @RequestParam("cinfo") String cinfo,
                             @RequestParam("cuid") Integer cuid) {
        // 创建评论对象
        Comment comment = new Comment();
        comment.setCpid(cpid);
        comment.setCinfo(cinfo);
        comment.setCuid(cuid);

        // 保存评论
        commentService.addComment(comment);

        // 评论添加成功后，重定向回帖子详情页面
        return "redirect:/postDetail/" + cpid;
    }
}

