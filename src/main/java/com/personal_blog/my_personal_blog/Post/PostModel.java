package com.personal_blog.my_personal_blog.Post;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "posts")
public class PostModel {
    @Id
    private String id;
    private String title;
    private String slug;
    private String content;
    private Enum<Status> status;
    private String coverImageUrl;
    private Date createdAt;
    private Date modifiedAt;
    private Date deletedAt;
    private List<String> tags;
    private String authorName;
    private String authorId;
}
