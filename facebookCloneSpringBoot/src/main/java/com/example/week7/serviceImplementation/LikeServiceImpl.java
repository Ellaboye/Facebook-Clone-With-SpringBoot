package com.example.week7.serviceImplementation;

import com.example.week7.Services.LikesService;
import com.example.week7.model.Likes;
import com.example.week7.model.Users;
import com.example.week7.model.Post;
import com.example.week7.repository.LikesRepository;
import com.example.week7.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikesService {

    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private PostRepository postRepository;

    /**
     * CREATE operation on Comment
     * @param person
     * @param postId
     * @param action
     * @return boolean(true for successful creation and false on failure on liked/unliked post)
     * */
    public boolean likePost(Users person, Long postId, String action){
        boolean result = false;

        Post post = postRepository.findById(postId).get();

        try{
            Likes like = new Likes();
            like.setPerson(person);
            like.setPost(post);

            if(action.equals("1")){
                likesRepository.save(like);
                System.out.println("save");
            }else{
                likesRepository.deleteLikesByPostAndPerson(post,person);
                System.out.println("delete");
            }

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
