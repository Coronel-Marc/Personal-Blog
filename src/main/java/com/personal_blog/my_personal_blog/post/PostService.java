package com.personal_blog.my_personal_blog.post;

import com.personal_blog.my_personal_blog.dto.PostCreateDTO;
import com.personal_blog.my_personal_blog.shared.enums.Role;
import com.personal_blog.my_personal_blog.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public PostModel createPost(PostCreateDTO postDTO, UserModel author){
        return null;
    }

    public PostModel updatePost(String id, PostCreateDTO postUpateDTO){
        return null;
    }

    public PostModel getPostBySlug(String slug){
        return null;
    }
    public PostModel getPostById(String id){
        return null;
    }
    public PostModel getAllPublicPosts(Pageable pageable){
        return null;
    }
}
