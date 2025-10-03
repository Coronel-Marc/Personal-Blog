package com.personal_blog.my_personal_blog.comment;

import com.personal_blog.my_personal_blog.dto.commentDTO.CommentCreateDTO;
import com.personal_blog.my_personal_blog.dto.commentDTO.CommentResponseDTO;
import com.personal_blog.my_personal_blog.dto.commentDTO.CommentUpdateDTO;
import com.personal_blog.my_personal_blog.exceptions.BadRequestException;
import com.personal_blog.my_personal_blog.exceptions.ForbiddenAccessException;
import com.personal_blog.my_personal_blog.exceptions.ResourceNotFoundException;
import com.personal_blog.my_personal_blog.post.PostRepository;
import com.personal_blog.my_personal_blog.user.UserModel;
import com.personal_blog.my_personal_blog.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.regex.Pattern;

@Service
public class CommentService {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    @Autowired
    private CommentRepository repository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService service;

    public CommentResponseDTO createComment(
            String postId,
            CommentCreateDTO commentDTO,
            UserModel author){

        postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));

        CommentModel newComment = new CommentModel();
        newComment.setPostId(postId);
        newComment.setContent(commentDTO.getContent());
        newComment.setAuthorId(author.getId());
        newComment.setAuthorName(author.getName());

        CommentModel savedComment = repository.save(newComment);
        return toResponseDTO(savedComment);

    }

    public CommentResponseDTO getCommentDTOById(String id){
        CommentModel comment = getCommentById(id);

        return toResponseDTO(comment);
    }

    public Page<CommentResponseDTO> getAllCommentsDTOByPostId(String postId, Pageable pageable){
        Page<CommentModel> comment = repository.findByPostId(postId, pageable);

        return comment.map(this::toResponseDTO);
    }

    public CommentResponseDTO updateCommentById(
            String commentId,
            String postId,
            CommentUpdateDTO commentDTO,
            UserDetails currentUser){

        CommentModel comment = getCommentById(commentId);

        UserModel author = service.findByEmail(currentUser.getUsername());

        boolean isAdmin = currentUser.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if(!comment.getAuthorId().equals(author.getId()) && !isAdmin){
            throw new ForbiddenAccessException("Você não tem permissão para alterar esse comentário");
        } else if (!comment.getPostId().equals(postId)) {
            throw new BadRequestException("Requisição inconsistente: O comentário não pertence ao post especificado.");
        }

        comment.setContent(commentDTO.getContent());

        repository.save(comment);

        return toResponseDTO(comment);
    }

    public void deleteCommentById(
            String commentId,
            String postId,
            UserDetails currentUser){
        CommentModel comment = getCommentById(commentId);

        UserModel author = service.findByEmail(currentUser.getUsername());

        boolean isAdmin = currentUser.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if(!comment.getAuthorId().equals(author.getId()) && !isAdmin){
            throw new ForbiddenAccessException("Você não tem permissão para deletar esse comentário.");
        } else if (!comment.getPostId().equals(postId)) {
            throw new BadRequestException("Requisição inconsistente: O comentário não pertence ao post especificado.");
        }

        comment.setDeletedAt(Instant.now());

        repository.save(comment);
    }








    // -- UTILITIES METHODS --

    private CommentResponseDTO toResponseDTO(CommentModel commentModel){
        return new CommentResponseDTO(
                commentModel.getId(),
                commentModel.getContent(),
                commentModel.getAuthorId(),
                commentModel.getCreatedAt()
        );
    }

    private CommentModel getCommentById(String id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado."));
    }
}
