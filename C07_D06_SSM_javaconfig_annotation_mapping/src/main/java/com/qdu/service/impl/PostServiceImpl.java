package com.qdu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdu.entity.PostWithUser;
import com.qdu.entity.User;
import com.qdu.mapper.PostMapper;
import com.qdu.entity.Post;
import com.qdu.mapper.UserMapper;
import com.qdu.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;


    public Integer addPost(Post post) {
        postMapper.insert(post); // 插入帖子
        Integer postId = post.getPid(); // 获取生成的主键
        if (postId == null) {
            throw new IllegalStateException("帖子插入失败，未生成主键值！");
        }
        return postId;
    }

    public void uploadPostImages(Integer postId, String imagePaths) {
        postMapper.updatePostImages(postId, imagePaths);
    }

    public List<Post> getAllPosts() {
        return postMapper.selectAll();
    }

    // 根据关键词搜索帖子
    public List<Post> searchPosts(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return getAllPosts(); // 如果关键词为空，返回所有帖子
        }
        return postMapper.searchPostsByKeyword("%" + keyword + "%");
    }

    public Post getPostById(Integer postId) {
        return postMapper.selectByPrimaryKey(postId);
    }

    //个人主页列表帖子
    public List<Post> listMyPost(Integer puid) {
        return postMapper.listMyPost(puid);
    }

    // 删除帖子
    public boolean deletePostById(Integer pid) {
        return postMapper.deleteByPrimaryKey(pid) > 0;
    }

    public PostWithUser getPostWithUserById(Integer postId) {
        // 获取帖子详情
        Post post = postMapper.selectByPrimaryKey(postId);

        // 获取发帖用户信息
        User user = userMapper.selectByPrimaryKey(post.getPuid());

        // 构造 PostWithUser
        PostWithUser postWithUser = new PostWithUser();
        postWithUser.setPost(post);
        postWithUser.setUser(user);

        return postWithUser;
    }

    public Integer countPost(){
        return postMapper.countPost();
    }

    public PageInfo<Post> getAllPostsWithPagination(int pageNum, int pageSize) {
        // 设置分页参数：页码和每页大小
        PageHelper.startPage(pageNum, pageSize);
        // 调用 Mapper 获取帖子列表
        List<Post> posts = postMapper.selectAll();
        // 使用 PageInfo 包装分页结果
        return new PageInfo<>(posts);
    }
}
