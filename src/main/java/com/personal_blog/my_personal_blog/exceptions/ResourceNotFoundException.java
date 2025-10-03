package com.personal_blog.my_personal_blog.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String s) {
        super(s);
    }
}
