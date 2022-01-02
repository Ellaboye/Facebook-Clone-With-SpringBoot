package com.example.week7.Services;

import com.example.week7.POJO.PostMapper;
import com.example.week7.model.Users;
import com.example.week7.model.Post;

import java.util.List;

public interface PostService {
    boolean createPost(Long userId, Post post);
    List<PostMapper> getPost(Users currentUser);
}
