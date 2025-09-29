package com.personal_blog.my_personal_blog.dto;

import com.personal_blog.my_personal_blog.shared.enums.Status;

import java.util.List;

public class PostCreateDTO {

    private String title, content;
    private List<String> tags;
    private Status status;

    public PostCreateDTO(){}
    public PostCreateDTO(String title, String content, Status status, List<String> tags){
        this.title = title;
        this.content = content;
        this.status = status;
        this.tags = tags;
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
}