package com.example.week7.serviceImplementation;

import com.example.week7.POJO.PostMapper;
import com.example.week7.Services.PostService;
import com.example.week7.model.Comment;
import com.example.week7.model.Likes;
import com.example.week7.model.Users;
import com.example.week7.model.Post;
import com.example.week7.repository.CommentRepository;
import com.example.week7.repository.LikesRepository;
import com.example.week7.repository.UsersRepository;
import com.example.week7.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    LikesRepository likesRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UsersRepository personRepository;

    /**
     * CREATE operation on Post
     * @param userId
     * @param post
     * @return boolean(true for successful creation and false on failure to create)
     * */
    public boolean createPost(Long userId, Post post) {
        boolean result = false;

        try {
            Users user = personRepository.findById(userId).get();

            if(user != null){
                post.setChecker("ACTIVE");
                postRepository.save(post);
                result = true;
            }else result = false;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * GET operation on Post
     * @param postId
     * @return boolean(true for successful creation and false on failure to create)
     * */
    public List<Post> getPostById(Long postId){
        List<Post> postList = new ArrayList<>();

        try{
            postList= postRepository.findPostByPostId(postId);
        }catch(Exception e){
            System.out.println("Something went wrong1 "+e.getMessage());
        }

        return postList;
    }

    /**
     * GET by id operation on Post
     * @params postId
     * @return post object
     * */
    public List<PostMapper> getPost(Users currentUser) {
        List<PostMapper> posts = new ArrayList<>();

        try {
            //get all posts
            List<Post> postData = postRepository.findAllByCheckerIsOrderByPostIdDesc("ACTIVE");

            for (Post postEach:postData) {

                PostMapper post = new PostMapper();
                post.setId(postEach.getPostId());
                post.setTitle(postEach.getTitle());
                post.setBody(postEach.getBody());
                post.setImageName("/images/"+postEach.getImageName());
                post.setName(postEach.getPerson().getLastname()+ " "+ postEach.getPerson().getFirstname());

                //the total number of likes on this particular post
                List<Likes> numberOfLikes = likesRepository.findAllByPostPostId(postEach.getPostId());
                int likeCount = numberOfLikes.size();
                post.setNoLikes(likeCount);

                //the total number of comments on this particular post
                List<Comment> noOfComment = commentRepository.findAllByPostPostId(postEach.getPostId());
                int commentCount = noOfComment.size();
                post.setNoComments(commentCount);

                //return true if current user liked this post, else false
                List<Likes> postLiked = likesRepository.findAllByPostPostIdAndPersonId(postEach.getPostId(), currentUser.getId());
                if(postLiked.size() > 0){
                    post.setLikedPost(true);
                }

                posts.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return posts;
    }

    /**
     * CREATE operation on Comment
     * @param person
     * @param postId
     * @param title
     * @param body
     * @return boolean(true for successful update and false on failure on post)
     * */
    public boolean editPost(Users person, Long postId, String title, String body) {
        boolean status = false;

        try {
            Post post = postRepository.findById(postId).get();
            post.setTitle(title);
            post.setBody(body);
            postRepository.save(post);

            status = true;

        }catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    /**
     * DELETE operation on Post
     * @param postId
     * @param personId
     * @return boolean(true for successful creation and false on failure to create)
     * */
    public boolean deletePost(Long postId, Long personId){
        boolean status =  false;

        try {

            Post post = postRepository.findPostByPostIdAndPersonId(postId, personId);

            if(post != null){
                post.setChecker("INACTIVE");
                postRepository.save(post);
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}

