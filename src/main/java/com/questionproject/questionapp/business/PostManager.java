package com.questionproject.questionapp.business;

import com.questionproject.questionapp.dataAccess.PostDao;
import com.questionproject.questionapp.entities.Like;
import com.questionproject.questionapp.entities.Post;
import com.questionproject.questionapp.entities.User;
import com.questionproject.questionapp.requests.PostCreateRequest;
import com.questionproject.questionapp.requests.PostUpdateRequest;
import com.questionproject.questionapp.responses.LikeResponse;
import com.questionproject.questionapp.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostManager {
    private PostDao postDao;
    private UserManager userManager;
    private LikeManager likeManager;
    public PostManager(PostDao postDao,
                       UserManager userManager) {
        this.postDao = postDao;
        this.userManager= userManager;
    }
    // kurucuyla atarsam birbirlerini çağırırlar sonsuza gider.
    //@Autowired olmadığı zaman nullpointerexception veriyor!
    @Autowired
    public void setLikeManager(LikeManager likeManager){
        this.likeManager = likeManager;
    }

    public List<PostResponse> getAllPosts(Long userId) {
        List<Post> listPost;
        if(userId != null) {
            listPost = postDao.findByUserId(userId);
        }
        // her bir post için gidip onun like'larını çek.
        // PostResponse objesi oluştururken o like listesini de parametre olarak ver.
        else{ listPost =  postDao.findAll();}

        return listPost.stream().map(post -> {
            List<LikeResponse> likes = likeManager.getAllLikesWithParams(null, post.getId());
            return new PostResponse(post, likes);
        }).collect(Collectors.toList());

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
