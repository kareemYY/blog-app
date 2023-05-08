package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImpl  implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private Post mapintoPost (PostDTO postDTO){
        Post post = new Post();
        post.setTitle       (postDTO.getTitle());
        post.setContent     (postDTO.getContent());
        post.setDescription (postDTO.getDescription());
        return post;
    }

    private PostDTO mapintoDto (Post post){
        PostDTO postDTO= new PostDTO();
        postDTO.setId          (post.getId());
        postDTO.setContent     (post.getContent());
        postDTO.setDescription (post.getDescription());
        postDTO.setTitle       (post.getTitle());
        return postDTO;
    }

    @Override
    public PostDTO createpost(PostDTO postDTO) {
        Post post =mapintoPost(postDTO);
        Post savedPost = postRepository.save(post);
        PostDTO postResponse = mapintoDto(savedPost);
        return postResponse;
    }



    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts =postRepository.findAll();
        List<PostDTO> postDTOS =new ArrayList<>();
        posts.forEach(post -> postDTOS.add(mapintoDto(post)));
        return postDTOS;
    }

    @Override
    public PostDTO getOnePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        return mapintoDto(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO) {
        Post post=postRepository.findById(postDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Post","id",postDTO.getId()));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        postRepository.save(post);
        return mapintoDto(post);
    }

    @Override
    public String deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id" ,id));
        postRepository.delete(post);
        return "post deleted with ID : " + id ;
    }
}
