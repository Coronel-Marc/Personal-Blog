package com.personal_blog.my_personal_blog.dto.postDTO;

import com.personal_blog.my_personal_blog.shared.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public class PostCreateDTO {

    @NotBlank
    @Size(max = 250)
    private String title;
    @NotBlank
    private String content;
    private List<String> tags;
    @NotNull
    private Status status;
    @URL(message = "URL da imagem de capa inválida")
    private String coverImageUrl;

    public PostCreateDTO(){}
    public PostCreateDTO(String title, String content, Status status, List<String> tags, String coverImageUrl){
        this.title = title;
        this.content = content;
        this.status = status;
        this.tags = tags;
        this.coverImageUrl = coverImageUrl;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public List<String> getTags(){
        return tags;
    }
    public void setTags(List<String> tags){
        this.tags = tags;
    }

    public Status getStatus(){
        return status;
    }
    public void setStatus(Status status){
        this.status = status;
    }

    public String getCoverImageUrl() { return coverImageUrl; }

    public void setCoverImageUrl(String coverImageUrl) { this.coverImageUrl = coverImageUrl; }
}