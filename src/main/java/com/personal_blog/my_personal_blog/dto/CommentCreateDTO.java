package com.personal_blog.my_personal_blog.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentCreateDTO {

    @NotBlank
    private String content;
    private String postId;
    private String authorId;

    public CommentCreateDTO(){}
    public CommentCreateDTO(String content, String postId, String authorId){
        this.content = content;
        this.postId = postId;
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
