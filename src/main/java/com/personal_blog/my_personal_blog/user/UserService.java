package com.personal_blog.my_personal_blog.user;

import com.personal_blog.my_personal_blog.dto.UserCreateDTO;
import com.personal_blog.my_personal_blog.dto.UserUpdateDTO;
import com.personal_blog.my_personal_blog.shared.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserModel newUser (UserCreateDTO userDTO) {
        UserModel user = new UserModel();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setRole(Collections.singleton(Role.ROLE_USER));

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);
        return repository.save(user);
    }

    public UserModel modifieUser (String id, UserUpdateDTO userDTO) {
        //Lógica de modificação aqui
        UserModel user = getUserById(id);
        user.setName(userDTO.getName());
        user.setProfileImageUrl(userDTO.getImageProfileUrl());

        return repository.save(user);
    }

    public UserModel getUserById (String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

    public List<UserModel> getAllUsers (){
        return repository.findAll();
    }

    public void deleteById (String id) {
        UserModel user = getUserById(id);
        repository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado nesse e-mail:" + username));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
        );
    }

    // -- FIND BY EMAIL METHOD --
    public UserModel findByEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado nesse e-mail:" + email));
    }
}

