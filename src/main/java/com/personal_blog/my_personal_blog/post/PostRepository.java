package com.personal_blog.my_personal_blog.post;

import com.personal_blog.my_personal_blog.shared.enums.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<PostModel, String> {

    Page<PostModel> findByAuthorId(String authorId, Pageable pageable);
    Optional<PostModel> findBySlug(String slug);
    Page<PostModel> findByStatus(Status status, Pageable pageable);
}
