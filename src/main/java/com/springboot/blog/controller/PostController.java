package com.springboot.blog.controller;


import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<>(postService.createpost(postDTO), HttpStatus.CREATED);
    }


    @GetMapping
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getOnePost(@PathVariable("id") long id){
        return new ResponseEntity<>(postService.getOnePost(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable ("id") long id){
        postDTO.setId(id);
        return new ResponseEntity<>(postService.updatePost(postDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") long id){
        return postService.deletePost(id);
    }














}
