package com.personal_blog.my_personal_blog.post;

import com.personal_blog.my_personal_blog.dto.PostCreateDTO;
import com.personal_blog.my_personal_blog.dto.PostResponseDTO;
import com.personal_blog.my_personal_blog.shared.enums.Status;
import com.personal_blog.my_personal_blog.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.text.Normalizer;
import java.time.Instant;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class PostService {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    @Autowired
    private PostRepository repository;

    public PostResponseDTO createPost(PostCreateDTO createDTO, UserModel author){
        PostModel postModel = new PostModel();

        postModel.setTitle(createDTO.getTitle());
        postModel.setContent(createDTO.getContent());
        postModel.setTags(createDTO.getTags());
        postModel.setStatus(createDTO.getStatus());

        postModel.setAuthorId(author.getId());
        postModel.setAuthorName(author.getName());

        postModel.setSlug(toSlug(createDTO.getTitle()));

        PostModel savedPostModel = repository.save(postModel);

        return toResponseDTO(savedPostModel);
    }


    public PostResponseDTO getPostDTOById(String id){
        PostModel post = getPostById(id);

        return toResponseDTO(post);
    }
    public PostResponseDTO getPostDTOBySlug(String slug){
        PostModel post = getPostBySlug(slug);

        return toResponseDTO(post);
    }
    public Page<PostResponseDTO> getAllPublicPosts(Pageable pageable){
        Page<PostModel> postModel = repository.findByStatus(Status.PUBLISHED, pageable);

        return postModel.map(this::toResponseDTO);
    }

    public PostResponseDTO updatePost(String id, PostCreateDTO postUpateDTO){
        PostModel post = getPostById(id);
        // Adicionar lógica para verificar se usuario é o dono do post ou ADMIN
        post.setTitle(postUpateDTO.getTitle());
        post.setSlug(toSlug(postUpateDTO.getTitle()));
        post.setContent(postUpateDTO.getContent());
        post.setTags(postUpateDTO.getTags());
        post.setStatus(postUpateDTO.getStatus());

        return toResponseDTO(repository.save(post));
    }

    public void deletePostById(String id){
        PostModel post = getPostById(id);
        // Adicionar lógica para verificar se usuario é o dono do post ou ADMIN

        post.setDeletedAt(Instant.now());

        repository.save(post);
    }

    // -- UTILITIES FUNCTIONS --
    private String toSlug(String input){
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    private PostResponseDTO toResponseDTO(PostModel postModel){
        return new PostResponseDTO(
                postModel.getId(),
                postModel.getTitle(),
                postModel.getContent(),
                postModel.getSlug(),
                postModel.getAuthorName(),
                postModel.getTags(),
                postModel.getStatus(),
                postModel.getCreatedAt()
        );
    }

    private PostModel getPostById(String id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado"));
    }

    private PostModel getPostBySlug(String slug){
        return repository.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado"));
    }



}
