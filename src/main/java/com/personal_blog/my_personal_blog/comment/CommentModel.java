package com.personal_blog.my_personal_blog.comment;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "comments")
public class CommentModel {

    @Id
    private String id;
    private String postId;
    private String content;
    private String authorName;
    private String authorId;
    private Date createdAt;
}
