package com.personal_blog.my_personal_blog.exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String s){
        super(s);
    }
}
