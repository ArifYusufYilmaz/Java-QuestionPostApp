package com.questionproject.questionapp.business;

import com.questionproject.questionapp.dataAccess.CommentDao;
import com.questionproject.questionapp.entities.Comment;
import com.questionproject.questionapp.entities.Post;
import com.questionproject.questionapp.entities.User;
import com.questionproject.questionapp.requests.CommentCreateRequest;
import com.questionproject.questionapp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentManager {
    private CommentDao commentDao;
    private UserManager userManager;
    private PostManager postManager;


    public CommentManager(CommentDao commentDao, UserManager userManager, PostManager postManager) {
        this.commentDao = commentDao;
        this.userManager = userManager;
        this.postManager = postManager;
    }
/* public List<Comment> getAllComments() {
        return commentDao.findAll();
    }*/

    public Comment getOneCommentById(Long commentId) {
       return commentDao.findById(commentId).orElse(null);
    }

    public List<Comment> getAllCommentsWithParams(Long userId, Long postId) {
        System.out.println("fonksiyondayım");
        System.out.println("postId : "+ postId);
        System.out.println("userId : "+userId);
        if(userId != null && postId != null) {
            return commentDao.findByUserIdAndPostId(userId, postId);
        }
        else if(postId != null){
            return commentDao.findByPostId(postId);
        }
        else if(userId != null){

             return commentDao.findByUserId(userId);
        }
        else{
            return commentDao.findAll();
        }


    }

    public Comment createOneComment(CommentCreateRequest newCommentRequest) {
        User user = userManager.getOneUser(newCommentRequest.getUserId());
        Post post = postManager.getOnePostById(newCommentRequest.getPostId());
        if(user == null || post == null)
                    return null;
        Comment toComment = new Comment();
        toComment.setId(newCommentRequest.getId());
        toComment.setUser(user);
        toComment.setPost(post);
        toComment.setText(newCommentRequest.getText());
        return commentDao.save(toComment);
    }

    public Comment updateOneComment(Long commentId, CommentUpdateRequest updateComment) {
        Optional<Comment> comment = commentDao.findById(commentId);
        if(comment.isPresent()){
            Comment toUpdate = comment.get();
            toUpdate.setText(updateComment.getText());
            commentDao.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOneComment(Long commentId) {
        commentDao.deleteById(commentId);
    }
}
