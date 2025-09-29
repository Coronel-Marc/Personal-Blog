package com.personal_blog.my_personal_blog.post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<PostModel, String> {

    Optional<PostModel> findByAuthor(String name);
    Optional<PostModel> findBySlug(String slug);
}
