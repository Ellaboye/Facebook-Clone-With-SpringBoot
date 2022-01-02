package com.example.week7.Services;

import com.example.week7.POJO.CommentMapper;
import com.example.week7.model.Comment;
import com.example.week7.model.Users;

import java.util.List;

public interface CommentService {
    boolean createComment(Long userId, Long postId, Comment comment);
    public List<CommentMapper> getComments(Long postId);
    boolean editComment(Long commentId, Users person, Long postId, String comment);
    boolean deleteComment(Long commentId);
}
