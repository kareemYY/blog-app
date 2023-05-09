package com.springboot.blog;

import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootBlogRestApiApplicationTests {

	private PostService postService;

	@Autowired
	public SpringbootBlogRestApiApplicationTests(PostService postService) {
		this.postService = postService;
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

}
