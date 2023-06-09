package com.springboot.blog.controller;


import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.util.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }



    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO){
        return new ResponseEntity<>(postService.createpost(postDTO), HttpStatus.CREATED);
    }


    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo"  ,defaultValue = AppConstants.DEFAULT_PAGE_NUMBER    ,  required = false)   int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE      ,  required = false)   int pageSize,
            @RequestParam(value = "sortBy"  ,defaultValue = AppConstants.DEFAULT_SORT_BY        ,  required = false)   String sortBy,
            @RequestParam(value = "sortDir" ,defaultValue = AppConstants.DEFAULT_SORT_DIRECTION ,  required = false)   String sortDir){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getOnePost(@PathVariable("id") long id){
        return new ResponseEntity<>(postService.getOnePost(id),HttpStatus.OK);
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO,@PathVariable ("id") long id){
        postDTO.setId(id);
        return new ResponseEntity<>(postService.updatePost(postDTO),HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePost(@PathVariable("id") long id){
        return postService.deletePost(id);
    }














}
