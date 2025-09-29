package com.personal_blog.my_personal_blog.user;

import com.personal_blog.my_personal_blog.dto.UserCreateDTO;
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
    public ResponseEntity<List<UserModel>> getAll(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserModel> getById(@PathVariable String id){
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserModel> modifieUser(@PathVariable String id, @RequestBody @Validated UserUpdateDTO userDTO) {
        UserModel updatedUser = service.modifieUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserModel> insert(@RequestBody @Validated UserCreateDTO userDTO){
        UserModel newUser = service.newUser(userDTO);

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
