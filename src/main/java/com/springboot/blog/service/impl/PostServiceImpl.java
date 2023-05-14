package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostServiceImpl  implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }




    private Post mapintoPost (PostDTO postDTO){
        Post post=modelMapper.map(postDTO,Post.class);
        return post;
    }

    private PostDTO mapintoDto (Post post){
        PostDTO postDTO=modelMapper.map(post,PostDTO.class);
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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable=PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts =postRepository.findAll(pageable);
        List<Post> postList=posts.getContent();
        List<PostDTO> postDTOS =new ArrayList<>();
        postList.forEach(post -> postDTOS.add(mapintoDto(post)));
        PostResponse postResponse= new PostResponse(postDTOS,pageNo+1,pageSize,
                                    posts.getTotalElements(),posts.getTotalPages(),posts.isLast());


        return postResponse;
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
