package com.personal_blog.my_personal_blog.post;

import com.personal_blog.my_personal_blog.dto.PostCreateDTO;
import com.personal_blog.my_personal_blog.dto.UserUpdateDTO;
import com.personal_blog.my_personal_blog.user.UserModel;
import com.personal_blog.my_personal_blog.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostService service;

    @Autowired
    private UserService userService;

    // --- PUBLIC ENDPOINTS ---

    @GetMapping
    public ResponseEntity<Page<PostModel>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAllPublicPosts(pageable));
    }
    @GetMapping(value = "/{slug}")
    public ResponseEntity<PostModel> getBySlug(@PathVariable String slug){
        return ResponseEntity.ok(service.getPostBySlug(slug));
    }

    // -- PROTECTED ENDPOINTS ---

    @PostMapping
    public ResponseEntity<PostModel> create(@RequestBody @Validated PostCreateDTO postDTO, @AuthenticationPrincipal UserDetails userDetails){
        UserModel author = userService.findByEmail(userDetails.getUsername());
        author.setId("Id");
        author.setName(userDetails.getUsername());

        PostModel newPost = service.createPost(postDTO, author);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newPost.getId()).toUri();
        return ResponseEntity.created(location).body(newPost);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostModel> update(@PathVariable String id, @RequestBody @Validated PostCreateDTO postUpdateDTO){
        return null; // To be Implemented
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PostModel> delete(@PathVariable String id){
        return null; // To be implemented
    }
}
