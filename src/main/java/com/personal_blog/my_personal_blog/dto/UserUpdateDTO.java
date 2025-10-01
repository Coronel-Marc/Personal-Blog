package com.personal_blog.my_personal_blog.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class UserUpdateDTO {

    @NotBlank
    private String name;
    @URL
    private String imageProfileUrl;

    public UserUpdateDTO(){}
    public UserUpdateDTO(String name, String imageProfileUrl) {
        this.name = name;
        this.imageProfileUrl = imageProfileUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageProfileUrl() {
        return imageProfileUrl;
    }

    public void setImageProfileUrl(String imageProfileUrl) {
        this.imageProfileUrl = imageProfileUrl;
    }
}