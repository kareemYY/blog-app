package com.springboot.blog.payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private long id;

    @NotEmpty(message = "Body Can not Be Empty")
    @NotBlank
    @Size(min = 10,message = "body can not be less than 10 char")
    private String body;

    @NotEmpty(message = "Email can not be Empty")
    @NotBlank
    @Email
    private String email;

    @NotEmpty(message = "Name Can not be empty")
    @NotBlank
    private String name;
}
