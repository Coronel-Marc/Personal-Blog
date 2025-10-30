package com.personal_blog.my_personal_blog.post;

import com.personal_blog.my_personal_blog.dto.postDTO.PostCreateDTO;
import com.personal_blog.my_personal_blog.dto.postDTO.PostResponseDTO;
import com.personal_blog.my_personal_blog.user.UserModel;
import com.personal_blog.my_personal_blog.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private PostService service;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    // --- PUBLIC ENDPOINTS ---

    @GetMapping
    public ResponseEntity<Page<PostResponseDTO>> getAll(Pageable pageable, @AuthenticationPrincipal UserDetails currentUser) {
        boolean isAdmin = false;
        if (currentUser != null){
            isAdmin = currentUser.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch("ROLE_ADMIN"::equals);
        }
        if (isAdmin){
            log.info("Usuario admin detectado. Buscando todos os posts.");
            return ResponseEntity.ok(service.getAllPublicPostsForAdmin(pageable));
        } else {
            log.info("Usuario não admin ou anônimo. Buscando apenas posts públicos.");
            return ResponseEntity.ok(service.getAllPublicPosts(pageable));
        }
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
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable String id, @RequestBody @Validated PostCreateDTO postUpdateDTO, @AuthenticationPrincipal UserDetails userDetails){
        PostResponseDTO updatedPost = service.updatePost(id, postUpdateDTO, userDetails);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id, @AuthenticationPrincipal UserDetails userDetails){
        service.deletePostById(id, userDetails);

        return ResponseEntity.noContent().build();
    }
}
