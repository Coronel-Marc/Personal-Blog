package com.personal_blog.my_personal_blog.comment;

import com.personal_blog.my_personal_blog.dto.CommentCreateDTO;
import com.personal_blog.my_personal_blog.dto.CommentResponseDTO;
import com.personal_blog.my_personal_blog.dto.CommentUpdateDTO;
import com.personal_blog.my_personal_blog.user.UserModel;
import com.personal_blog.my_personal_blog.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentService service;
    @Autowired
    private UserService userService;

    // --- PUBLIC ENDPOINTS ---


    // -- PROTECTED ENDPOINTS ---

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(
            @PathVariable String postId,
            @RequestBody @Validated CommentCreateDTO commentCreateDTO,
            @AuthenticationPrincipal UserDetails userDetails){

        UserModel author = userService.findByEmail(userDetails.getUsername());

        CommentResponseDTO newComment = service.createComment(postId,commentCreateDTO,author);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newComment.getId()).toUri();
        return ResponseEntity.created(location).body(newComment);
    }
    @GetMapping
    public ResponseEntity<Page<CommentResponseDTO>> getAllCommentByPostId(
            @PathVariable String postId,
            Pageable pageable
    ){
        return ResponseEntity.ok(service.getAllCommentsDTOByPostId(postId, pageable));
    }

    @PutMapping(value = "/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateCommentById(
            @PathVariable String commentId,
            @PathVariable String postId,
            @RequestBody @Validated CommentUpdateDTO commentUpdateDTO,
            @AuthenticationPrincipal UserDetails userDetails){

        CommentResponseDTO updatedComment = service.updateCommentById(commentId, postId, commentUpdateDTO, userDetails);

        return ResponseEntity.ok(updatedComment);

    }

    @DeleteMapping(value = "/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable String commentId,
            @PathVariable String postId,
            @AuthenticationPrincipal UserDetails userDetails){
        service.deleteCommentById(commentId, postId,userDetails);

        return ResponseEntity.noContent().build();
    }
}
