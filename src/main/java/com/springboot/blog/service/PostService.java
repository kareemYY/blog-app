package com.springboot.blog.service;

import com.springboot.blog.payload.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createpost(PostDTO postDTO);

    List<PostDTO> getAllPosts();

    PostDTO getOnePost(long id);

    PostDTO updatePost(PostDTO postDTO);

    String deletePost(long id);

}
