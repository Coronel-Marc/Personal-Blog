package com.personal_blog.my_personal_blog.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentUpdateDTO {

    @NotBlank
    String content;

    public CommentUpdateDTO(){}
    public CommentUpdateDTO(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
