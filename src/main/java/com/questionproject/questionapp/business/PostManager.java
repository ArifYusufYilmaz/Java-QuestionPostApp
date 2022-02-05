package com.questionproject.questionapp.business;

import com.questionproject.questionapp.dataAccess.PostDao;
import com.questionproject.questionapp.entities.Post;
import com.questionproject.questionapp.entities.User;
import com.questionproject.questionapp.requests.PostCreateRequest;
import com.questionproject.questionapp.requests.PostUpdateRequest;
import com.questionproject.questionapp.responses.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostManager {
    private PostDao postDao;
    private UserManager userManager;
    public PostManager(PostDao postDao,UserManager userManager) {
        this.postDao = postDao;
        this.userManager= userManager;
    }

    public List<PostResponse> getAllPosts(Long userId) {
        List<Post> list;
        if(userId != null) {
            list = postDao.findByUserId(userId);

        }
        else{ list =  postDao.findAll(); }
        return list.stream().map(p -> new PostResponse(p)).collect(Collectors.toList());



    }

    public Post getOnePostById(Long postId) {
        return postDao.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userManager.getOneUserById(newPostRequest.getUserId());
        if(user == null)
            return null; // şimdilik.
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postDao.save(toSave);
    }

    //tüm alanları değil, update etmek istediğimiz alanları getiricez.
    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
       // bana verilen id ile gerçekten bu post var mı?
        Optional<Post> post = postDao.findById(postId);
        if(post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            postDao.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOnePostById(Long postId) {
         postDao.deleteById(postId);
    }
}
