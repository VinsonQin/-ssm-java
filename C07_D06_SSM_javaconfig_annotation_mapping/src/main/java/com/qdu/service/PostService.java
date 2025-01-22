package com.qdu.service;

import com.github.pagehelper.PageInfo;
import com.qdu.entity.Post;
import com.qdu.entity.PostWithUser;

import java.util.List;

public interface PostService {

    Integer addPost(Post post);
    void uploadPostImages(Integer postId, String imagePaths);
    List<Post> getAllPosts();
    List<Post> searchPosts(String keyword);
    Post getPostById(Integer postId);
    List<Post> listMyPost(Integer puid);
    boolean deletePostById(Integer pid);
    PostWithUser getPostWithUserById(Integer postId);
    Integer countPost();
    PageInfo<Post> getAllPostsWithPagination(int pageNum, int pageSize);
}
