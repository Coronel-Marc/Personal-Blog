package com.personal_blog.my_personal_blog.user;

import com.personal_blog.my_personal_blog.dto.UserCreateDTO;
import com.personal_blog.my_personal_blog.dto.UserResponseDTO;
import com.personal_blog.my_personal_blog.dto.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable String id){
        return ResponseEntity.ok(service.getUserDTOById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> modifieUser(@PathVariable String id, @RequestBody @Validated UserUpdateDTO userDTO) {
        UserResponseDTO updatedUser = service.modifieUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponseDTO> insert(@RequestBody @Validated UserCreateDTO userDTO){
        UserResponseDTO newUser = service.newUser(userDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).body(newUser);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
