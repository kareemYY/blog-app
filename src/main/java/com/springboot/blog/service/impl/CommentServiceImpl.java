package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private  CommentRepository commentRepository;
    private  PostRepository postRepository;
    private ModelMapper mapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }


    // HELPER METHOD
    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = mapper.map(comment,CommentDto.class);
        return commentDto;
    }
    // HELPER METHOD
    private Comment mapToComment(CommentDto commentDto) {
        Comment comment =mapper.map(commentDto,Comment.class);
        return comment;
    }

    // HELPER METHOD
    private Comment getComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "Id", postId));
        Comment comment =commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "Id", commentId));
        if (comment.getPost() !=post){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment Not Belong To This Post");
        }
        return comment;
    }


    @Override
    public CommentDto createComment(long post_id, CommentDto commentDto) {
        Post post = postRepository.findById(post_id).orElseThrow(() ->
                new ResourceNotFoundException("post", "id", post_id));
        Comment newComment = mapToComment(commentDto);
        newComment.setPost(post);
        Comment returnComment = commentRepository.save(newComment);
        return mapToDto(returnComment);
    }

    @Override
    public List<CommentDto> findAllComment(long postId) {
        /*Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("post", "id", postId));
        List<Comment> comments = post.getComments();*/  // OLD way
        List<Comment> commentList = commentRepository.findByPostId(postId);
        List<CommentDto> commentDtos = new ArrayList<>();
        commentList.forEach(comment -> commentDtos.add(mapToDto(comment)));
        return commentDtos;
        //another solution is : -> Create a query in commentRepo called ( " findByPostId and return list<> ")

    }

    @Override
    public CommentDto findCommentById(long postId, long commentId) {
        Comment comment = getComment(postId, commentId);
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, CommentDto commentDto) {
        Comment comment = getComment(postId, commentDto.getId());
        comment.setId(100100);
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }



    @Override
    public String deleteComment(long postId, long commentId) {
        Comment comment = getComment(postId, commentId);
        commentRepository.delete(comment);
        return "DONE";
    }


}