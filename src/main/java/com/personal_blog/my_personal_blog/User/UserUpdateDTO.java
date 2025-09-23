package com.personal_blog.my_personal_blog.User;

public class UserUpdateDTO{

    private String name;
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
