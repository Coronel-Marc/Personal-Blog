package com.personal_blog.my_personal_blog.post;

import com.personal_blog.my_personal_blog.dto.PostCreateDTO;
import com.personal_blog.my_personal_blog.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class PostService {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    @Autowired
    private PostRepository repository;

    public PostModel createPost(PostCreateDTO postDTO, UserModel author){
        PostModel newPost = new PostModel();
        newPost.setTitle(postDTO.getTitle());
        newPost.setContent(postDTO.getContent());
        newPost.setTags(postDTO.getTags());
        newPost.setStatus(postDTO.getStatus());

        newPost.setAuthorId(author.getId());
        newPost.setAuthorName(author.getName());

        newPost.setSlug(toSlug(postDTO.getTitle()));

        return repository.save(newPost);
    }


    public PostModel getPostById(String id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado"));
    }
    public PostModel getPostBySlug(String slug){
        return repository.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado"));
    }
    public Page<PostModel> getAllPublicPosts(Pageable pageable){
        //Adicionar lógica para buscar apenas posts com status PUBLISHED
        return repository.findAll(pageable);
    }

    public PostModel updatePost(String id, PostCreateDTO postUpateDTO){
        return null;
    }

    public PostModel deletePostById(String id){
        return null;
    }
    public PostModel deletePostBySlug(String slug){
        return null;
    }

    //Função utilitaria
    private String toSlug(String input){
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
