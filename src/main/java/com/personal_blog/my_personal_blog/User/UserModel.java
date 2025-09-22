package com.personal_blog.my_personal_blog.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
public class UserModel {
    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String profileImageUrl;
    private Enum<Role> role;
    private LocalDateTime createdAt;

}
