package com.example.week7.repository;

import com.example.week7.model.Likes;
import com.example.week7.model.Users;
import com.example.week7.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findAllByPostPostId(Long postId);
    List<Likes> findAllByPostPostIdAndPersonId(Long postId, Long personId);
    @Transactional
    void deleteLikesByPostAndPerson(Post post, Users person);
}
