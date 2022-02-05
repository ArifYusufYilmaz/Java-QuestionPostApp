package com.questionproject.questionapp.business;

import com.questionproject.questionapp.dataAccess.LikeDao;
import com.questionproject.questionapp.entities.Like;
import com.questionproject.questionapp.entities.Post;
import com.questionproject.questionapp.entities.User;
import com.questionproject.questionapp.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeManager {
    private LikeDao likeDao;
    private UserManager userManager;
    private PostManager postManager;

    public LikeManager(LikeDao likeDao, UserManager userManager, PostManager postManager) {
        this.likeDao = likeDao;
        this.userManager = userManager;
        this.postManager = postManager;
    }

    public List<Like> getAllLikesWithParams(Long userId, Long postId) {
        if(userId != null && postId != null){
            return likeDao.findByUserIdAndPostId(userId, postId);
        }
        else if(userId != null){
            return likeDao.findByUserId(userId);
        }
        else if(postId != null){
            return likeDao.findByPostId(postId);
        }else{
            return likeDao.findAll();
        }
    }

    public Like getOneLikeById(Long likeId) {
        return likeDao.findById(likeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest newLikeRequest) {
         User user = userManager.getOneUserById(newLikeRequest.getUserId());
         Post post = postManager.getOnePostById(newLikeRequest.getPostId());
         if(user == null || post == null){
             return null;
         }
         else {
             Like toLike = new Like();
             toLike.setId(newLikeRequest.getId());
             toLike.setUser(user);
             toLike.setPost(post);
             return likeDao.save(toLike);
         }

    }

    public void deleteOneLike(Long likeId) {
         likeDao.deleteById(likeId);
    }
}
