package com.springboot.blog.service;

import com.springboot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long post_id , CommentDto commentDto);


    List<CommentDto> findAllComment(long postId);

    CommentDto findCommentById(long postId , long commentId);

    CommentDto updateComment(long postId, CommentDto commentDto);

    String deleteComment(long postId , long commentId);
}
