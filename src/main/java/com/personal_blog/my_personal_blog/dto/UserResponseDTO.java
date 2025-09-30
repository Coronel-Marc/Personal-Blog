package com.personal_blog.my_personal_blog.dto;

import com.personal_blog.my_personal_blog.shared.enums.Role;

import java.util.Set;

public class UserResponseDTO {
    String id, name, email, profileImageUrl;
    Set<Role> roles;

    public UserResponseDTO(){}
    public UserResponseDTO(String id, String name, String email, String profileImageUrl, Set<Role> roles){
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Set<Role> getRole(){
        return roles;
    }
    public void setRole(Set<Role> roles){
        this.roles = roles;
    }
}
