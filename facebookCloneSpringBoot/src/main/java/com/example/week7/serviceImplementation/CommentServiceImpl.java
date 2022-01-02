package com.example.week7.serviceImplementation;

import com.example.week7.POJO.CommentMapper;
import com.example.week7.Services.CommentService;
import com.example.week7.model.Comment;
import com.example.week7.model.Users;
import com.example.week7.model.Post;
import com.example.week7.repository.CommentRepository;
import com.example.week7.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;




    /**
     * CREATE operation on Comment
     * @param comment
     * @param postId
     * @param userId
     * @return boolean(true for successful creation and false on failure on create)
     **/

    public boolean createComment(Long userId, Long postId, Comment comment){
        boolean result = false;


        try{
            Post post = postRepository.findById(postId).get();

            //set the post
            comment.setPost(post);
            commentRepository.save(comment);
            result = true;
        } catch(Exception ex){
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * GET operation on comment
     * @param postId
     * @return List of comments
     **/


    public List<CommentMapper> getComments(Long postId) {
        List<CommentMapper> comments = new ArrayList<>();

        try{
            List<Comment> commentsData = commentRepository.findAllByPostPostId(postId);
            for (Comment commentEach:commentsData) {
                CommentMapper comment = new CommentMapper();
                comment.setId(commentEach.getId());
                comment.setPostId(commentEach.getPost().getPostId());
                comment.setComment(commentEach.getComment());
                comment.setUsername(commentEach.getPerson().getLastname()+" "+commentEach.getPerson().getFirstname());
                comment.setTitle(commentEach.getPost().getTitle());
                comment.setImageName("/images/"+commentEach.getPost().getImageName());
                comment.setUserId(commentEach.getPerson().getId());

                comments.add(comment);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return comments;
    }

    /**
     * CREATE operation on Comment
     * @param comment
     * @param postId
     * @param commentId
     * @param person
     * @return boolean(true for successful creation and false on failure on comment update)
     * */
    public boolean editComment(Long commentId, Users person, Long postId, String comment) {
        boolean status = false;

        try {
            Post post = postRepository.findById(postId).get();

            Comment data = commentRepository.findCommentById(commentId);

            data.setComment(comment);
            data.setPerson(person);
            data.setPost(post);
            commentRepository.save(data);

            status = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * DELETE operation on Comment
     * @param commentId
     * @return boolean(true for successful deletion and false on failure to delete)
     * */
    public boolean deleteComment(Long commentId){
        boolean status =  false;

        try {
            commentRepository.deleteCommentById(commentId);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}

