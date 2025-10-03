package com.personal_blog.my_personal_blog.user;

import com.personal_blog.my_personal_blog.dto.userDTO.UserCreateDTO;
import com.personal_blog.my_personal_blog.dto.userDTO.UserResponseDTO;
import com.personal_blog.my_personal_blog.dto.userDTO.UserUpdateDTO;
import com.personal_blog.my_personal_blog.exceptions.ResourceNotFoundException;
import com.personal_blog.my_personal_blog.shared.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO newUser (UserCreateDTO userDTO) {
        UserModel user = new UserModel();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setRoles(Collections.singleton(Role.ROLE_USER));

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);
        UserModel savedUser = repository.save(user);
        return toUserResponseDTO(savedUser);
    }

    public UserResponseDTO modifieUser (String id, UserUpdateDTO userDTO) {
        //Lógica de modificação aqui
        UserModel user = getUserById(id);
        user.setName(userDTO.getName());
        user.setProfileImageUrl(userDTO.getImageProfileUrl());

        UserModel savedUser = repository.save(user);

        return toUserResponseDTO(savedUser);
    }

    public UserResponseDTO getUserDTOById (String id) {
        UserModel user = getUserById(id);
        return toUserResponseDTO(user);
    }

    public List<UserResponseDTO> getAllUsers (){
        List<UserModel> users = repository.findAll();

        return users.stream()
                .map(this::toUserResponseDTO)
                .collect(Collectors.toList());

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
    // -- UTILITIES METHODS

    private UserResponseDTO toUserResponseDTO(UserModel userModel){
        return new UserResponseDTO(
                userModel.getId(),
                userModel.getName(),
                userModel.getEmail(),
                userModel.getProfileImageUrl(),
                userModel.getRoles()
        );
    }

    // -- FIND BY EMAIL METHOD --
    public UserModel findByEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado nesse e-mail:" + email));
    }

    private UserModel getUserById (String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado com o id: " + id));
    }
}

