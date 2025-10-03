package com.personal_blog.my_personal_blog.exceptions;

public class ForbiddenAccessException extends RuntimeException{

    public ForbiddenAccessException(String s){
        super(s);
    }
}
