package com.personal_blog.my_personal_blog.post;

import com.personal_blog.my_personal_blog.dto.PostCreateDTO;
import com.personal_blog.my_personal_blog.dto.PostResponseDTO;
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
    public ResponseEntity<Page<PostResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAllPublicPosts(pageable));
    }
    @GetMapping(value = "/{slug}")
    public ResponseEntity<PostResponseDTO> getBySlug(@PathVariable String slug){
        return ResponseEntity.ok(service.getPostDTOBySlug(slug));
    }

    // -- PROTECTED ENDPOINTS ---

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody @Validated PostCreateDTO createDTO, @AuthenticationPrincipal UserDetails userDetails){
        UserModel author = userService.findByEmail(userDetails.getUsername());

        PostResponseDTO responseDTO = service.createPost(createDTO, author);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.getId()).toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable String id, @RequestBody @Validated PostCreateDTO postUpdateDTO){
        PostResponseDTO updatedPost = service.updatePost(id, postUpdateDTO);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id){
        service.deletePostById(id);

        return ResponseEntity.noContent().build();
    }
}
