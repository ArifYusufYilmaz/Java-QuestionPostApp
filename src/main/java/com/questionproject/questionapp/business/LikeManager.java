package com.questionproject.questionapp.business;

import com.questionproject.questionapp.dataAccess.LikeDao;
import com.questionproject.questionapp.entities.Like;
import com.questionproject.questionapp.entities.Post;
import com.questionproject.questionapp.entities.User;
import com.questionproject.questionapp.requests.LikeCreateRequest;
import com.questionproject.questionapp.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<LikeResponse> getAllLikesWithParams(Long userId, Long postId) {
        List<Like> listLikes;
        if(userId != null && postId != null){
            listLikes = likeDao.findByUserIdAndPostId(userId, postId);
        }
        else if(userId != null){
            listLikes = likeDao.findByUserId(userId);
        }
        else if(postId != null){
            listLikes = likeDao.findByPostId(postId);
        }else{
            listLikes = likeDao.findAll();
        }
        return listLikes.stream().map(like-> new LikeResponse(like)).collect(Collectors.toList());
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
