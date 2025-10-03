package com.personal_blog.my_personal_blog.dto.exceptionDTO;

import java.time.Instant;

public class StandardErrorDTO {
    String message;
    String error;
    String path;
    Integer status;
    Instant timestamp;

    public StandardErrorDTO(){}
    public StandardErrorDTO(String message, String error, String path, Integer status, Instant timestamp) {
        this.message = message;
        this.error = error;
        this.path = path;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
