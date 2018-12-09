package com.terwergreen.plugins.blog.service.impl;

import com.github.pagehelper.PageInfo;
import com.terwergreen.core.CommonService;
import com.terwergreen.plugins.blog.pojo.Post;
import com.terwergreen.plugins.blog.pojo.PostMeta;
import com.terwergreen.plugins.blog.service.PostService;

import java.util.List;
import java.util.Map;

/**
 * 文章相关API
 *
 * @author Terwer
 * @version 1.0
 * 2018/12/10 1:23
 **/
public class PostServiceImpl implements PostService {
    @Override
    public List<Post> getRecentPosts(Map paramMap) {
        return null;
    }

    @Override
    public Post getPostBySlug(String slug) {
        return null;
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public PageInfo<Post> getPostsByPage(Integer pageNum, Integer pageSize, Map paramMap) {
        return null;
    }

    @Override
    public List getUsersBlogs(String appkey, String username, String password) {
        return null;
    }

    @Override
    public Integer newPost(Post post) {
        return null;
    }

    @Override
    public boolean editPostById(Post post) {
        return false;
    }

    @Override
    public boolean deletePostBySlug(String postSlug) {
        return false;
    }

    @Override
    public boolean deletePostById(Integer postId) {
        return false;
    }

    @Override
    public boolean saveOrUpdatePostMeta(PostMeta postMeta) {
        return false;
    }
}
