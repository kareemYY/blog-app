package com.springboot.blog;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootBlogRestApiApplicationTests {

	private final PostService postService;
	private final CommentService commentService;

	@Autowired
	public SpringbootBlogRestApiApplicationTests(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}

	@Test
	void contextLoads() {
		PostDTO postDTO= new PostDTO();
		for (int i =1 ; i <= 50 ;i++){
			postDTO.setTitle("This is title Of Post " +i);
			postDTO.setContent("this is content of Post "+i);
			postDTO.setDescription("this is description of Post "+i);
			postService.createpost(postDTO);
		}


	}
/*
	@Test
	void createCommentTest(){
		for(long i = 1 ; i <=100 ;i++){
			for (long t=1 ;t<=1000; t++){
				CommentDto commentDto = new CommentDto(0,"comment test : "+ t +"  for post: " +i,
						"testemail@"+t,"test user :"+t);
				commentService.createComment(i,commentDto);
			}
		}

	}
*/








}
