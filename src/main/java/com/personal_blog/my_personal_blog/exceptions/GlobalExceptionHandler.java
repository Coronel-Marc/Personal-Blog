package com.personal_blog.my_personal_blog.exceptions;

import com.personal_blog.my_personal_blog.dto.exceptionDTO.StandardErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFound(ResourceNotFoundException exception, HttpServletRequest request){
        StandardErrorDTO errorDTO = new StandardErrorDTO();

        errorDTO.setTimestamp(Instant.now());
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setError("Resource Not Found");
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(ForbiddenAccessException.class)
    public ResponseEntity handleForbiddenAccess(ForbiddenAccessException exception, HttpServletRequest request){
        StandardErrorDTO errorDTO = new StandardErrorDTO();

        errorDTO.setTimestamp(Instant.now());
        errorDTO.setStatus(HttpStatus.FORBIDDEN.value());
        errorDTO.setError("Permission Denied");
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDTO);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handleBadRequest(BadRequestException exception, HttpServletRequest request){
        StandardErrorDTO errorDTO = new StandardErrorDTO();

        errorDTO.setTimestamp(Instant.now());
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDTO.setError("Inconsistent Request");
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<StandardErrorDTO> handleAuthenticationException(Exception exception, HttpServletRequest request) {
        StandardErrorDTO errorDTO = new StandardErrorDTO();

        errorDTO.setTimestamp(Instant.now());
        errorDTO.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorDTO.setError("Authentication failed");
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDTO);
    }

}
