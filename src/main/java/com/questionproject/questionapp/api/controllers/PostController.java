package com.questionproject.questionapp.api.controllers;

import com.questionproject.questionapp.business.PostManager;
import com.questionproject.questionapp.entities.Post;
import com.questionproject.questionapp.requests.PostCreateRequest;
import com.questionproject.questionapp.requests.PostUpdateRequest;
import com.questionproject.questionapp.responses.PostResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostManager postManager;

    public PostController(PostManager postManager) {
        this.postManager = postManager;
    }

    // Postlardan oluşan bir liste dönmeli.
    // RequestParam-> Bize gelen istekteki parametreli parse et ve sağımda bulunan değişkenlerin içerisine at.
    // Sıkıntı çıkaran Optinal' ı kullanmak yerine required=false diyerek userId alma zorunluluğunu ortadan kaldırdım.
    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam(required = false) Long userId) {
        return postManager.getAllPosts(userId);
    }
    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postManager.createOnePost(newPostRequest);
    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postManager.getOnePostById(postId);
    }

    @PutMapping("{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost ){
        return postManager.updateOnePostById(postId,updatePost);

    }
    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
         postManager.deleteOnePostById(postId);
    }

}
