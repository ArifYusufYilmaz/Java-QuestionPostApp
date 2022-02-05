package com.questionproject.questionapp.api.controllers;

import com.questionproject.questionapp.business.LikeManager;
import com.questionproject.questionapp.entities.Like;
import com.questionproject.questionapp.requests.LikeCreateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeManager likeManager;

    public LikeController(LikeManager likeManager) {
        this.likeManager = likeManager;
    }
    @GetMapping
    public List<Like> getAllLikes(@RequestParam(required=false, name="userId") Long userId,
                                  @RequestParam(required=false, name="postId") Long postId){
        return likeManager.getAllLikesWithParams(userId, postId);
    }
    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeManager.getOneLikeById(likeId);
    }
    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest newLikeRequest){
        return likeManager.createOneLike(newLikeRequest);
    }
    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
          likeManager.deleteOneLike(likeId);
    }
}
