package com.springboot.blog.service;

import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;

public interface PostService {

    PostDTO createpost(PostDTO postDTO);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getOnePost(long id);

    PostDTO updatePost(PostDTO postDTO);

    String deletePost(long id);

}
