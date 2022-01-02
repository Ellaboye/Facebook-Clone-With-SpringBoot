package com.example.week7.repository;

import com.example.week7.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostPostId(Long postId);
    Comment findCommentById(Long commentId);
    @Transactional
    void deleteCommentById(Long id);
}
