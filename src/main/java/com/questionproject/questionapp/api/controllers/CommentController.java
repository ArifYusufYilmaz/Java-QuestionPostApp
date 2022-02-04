package com.questionproject.questionapp.api.controllers;

import com.questionproject.questionapp.business.CommentManager;
import com.questionproject.questionapp.entities.Comment;
import com.questionproject.questionapp.requests.CommentCreateRequest;
import com.questionproject.questionapp.requests.CommentUpdateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentManager commentManager;

    public CommentController(CommentManager commentManager) {
        this.commentManager = commentManager;
    }
   /* @GetMapping
    public List<Comment> getAllComments(){
        return commentManager.getAllComments();
    }*/

    @GetMapping
    public List<Comment> getAllCommentsWithParams(@RequestParam(name="userId",required= false) Long userId,
                                                  @RequestParam(name="postId",required =false) Long postId)
    {
        return commentManager.getAllCommentsWithParams(userId,postId);
    }
    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentManager.getOneCommentById(commentId);
    }
    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest newCommentRequest){
        return commentManager.createOneComment(newCommentRequest);
    }
    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest updateComment)
    {
        return commentManager.updateOneComment(commentId, updateComment);
    }
    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
          commentManager.deleteOneComment(commentId);
    }
}









