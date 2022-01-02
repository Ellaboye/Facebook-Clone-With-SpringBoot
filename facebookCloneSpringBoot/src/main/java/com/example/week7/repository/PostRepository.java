package com.example.week7.repository;

import com.example.week7.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findPostByPostId(Long postId);
    @Transactional
    void deletePostByPostIdAndPersonId(Long postId, Long personId);
    Post findPostByPostIdAndPersonId(Long postId, Long personId);
    List<Post> findAllByCheckerIsOrderByPostIdDesc(String checker);
}
