package com.personal_blog.my_personal_blog.auth;

import com.personal_blog.my_personal_blog.dto.authDTO.LoginRequestDTO;
import com.personal_blog.my_personal_blog.dto.authDTO.LoginResponseDTO;
import com.personal_blog.my_personal_blog.security.JwtService;
import com.personal_blog.my_personal_blog.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private JwtService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO){

        var usernamePassword = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()
        );

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = service.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
