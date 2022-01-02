package com.example.week7.Services;

import com.example.week7.model.Users;

public interface LikesService {
    public boolean likePost(Users person, Long postId, String action);
}
