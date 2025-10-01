package com.personal_blog.my_personal_blog.dto;

public class CommentUpdateDTO {
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
