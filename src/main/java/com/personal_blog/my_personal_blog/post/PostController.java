package com.personal_blog.my_personal_blog.post;

import com.personal_blog.my_personal_blog.dto.PostCreateDTO;
import com.personal_blog.my_personal_blog.dto.UserUpdateDTO;
import com.personal_blog.my_personal_blog.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<List<PostModel>> getAll() {
        return null;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<PostModel> getById(@PathVariable String id){
        return ResponseEntity.ok(service.getPostById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostModel> modifiepost(@PathVariable String id, @RequestBody @Validated PostCreateDTO postUpdate,UserModel author){
        return service.createPost(postUpdate, author);
    }
}
