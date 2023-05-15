package com.springboot.blog.controller;


import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>( commentService.createComment(postId,commentDto),
                                    HttpStatus.CREATED);
    }


    @GetMapping("/{postId}/comments")
    public List<CommentDto> getAllComment(@PathVariable("postId") long postId){
        return commentService.findAllComment(postId);
    }



    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("commentId") long commentId,
                                                    @Valid @RequestBody CommentDto commentDto,
                                                    @PathVariable("postId") long postId){

        commentDto.setId(commentId);
        return new ResponseEntity<>(commentService.updateComment(postId,commentDto),HttpStatus.OK);
    }


    @DeleteMapping("/{postId}/comments/{commentId}")
    public  ResponseEntity<String> deleteComment(@PathVariable("postId") long postId,
                                                 @PathVariable("commentId") long commentId){
        return new ResponseEntity<>(commentService.deleteComment(postId,commentId),HttpStatus.OK);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public  ResponseEntity<CommentDto> getOneComment(@PathVariable("postId") long postId,
                                                 @PathVariable("commentId") long commentId){
        return new ResponseEntity<>(commentService.findCommentById(postId,commentId),HttpStatus.OK);
    }











}
