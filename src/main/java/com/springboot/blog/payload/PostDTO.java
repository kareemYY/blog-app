package com.springboot.blog.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Set;

@Data
public class PostDTO {

    private Long id;

    @NotEmpty
    @NotBlank
    @Size(min = 2,message = "Title can't be less than 2")
    private String title;

    @NotEmpty
    @NotBlank
    @Size(min = 10 , message = "post description should have at least 10 char")
    private String description;

    @NotEmpty
    private String content;

    private Set<CommentDto> comments;
}
