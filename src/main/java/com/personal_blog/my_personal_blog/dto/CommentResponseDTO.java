package com.personal_blog.my_personal_blog.dto;

import java.time.Instant;

public class CommentResponseDTO {
    String id, content, authorId;
    Instant createdAt;

    public CommentResponseDTO(){}
    public CommentResponseDTO(String id, String content, String authorId, Instant createdAt){
        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
