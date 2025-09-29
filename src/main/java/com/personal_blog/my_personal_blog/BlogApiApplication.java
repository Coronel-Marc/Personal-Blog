package com.personal_blog.my_personal_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class BlogApiApplication {

	public static void main(String[] args) {

        SpringApplication.run(BlogApiApplication.class, args);
	}

}
