package com.personal_blog.my_personal_blog.dto.authDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {
    @NotBlank(message = "O e-mail não pode ser vazio.")
    @Email(message = "O formato do e-mail é inválido.")
    private String email;

    @NotBlank(message = "A senha não pode ser vazia.")
    private String password;

    public LoginRequestDTO(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
