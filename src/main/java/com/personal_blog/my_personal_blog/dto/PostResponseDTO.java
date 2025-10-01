package com.personal_blog.my_personal_blog.dto;

import com.personal_blog.my_personal_blog.shared.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;

public class PostResponseDTO {

    private String id;
    @NotBlank
    @Size(max = 250)
    private String title;
    @NotBlank
    private String content;
    private String slug;
    private String authorName;
    private List<String> tags;
    @NotNull
    private Status status;
    private Instant createdAt;

    public PostResponseDTO(){}
    public PostResponseDTO(String id, String title, String content, String slug,String authorName, List<String> tags, Status status, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.slug = slug;
        this.authorName = authorName;
        this.tags = tags;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
