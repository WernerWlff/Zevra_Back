package com.zevra.zevra.controllers;

import com.zevra.zevra.dto.UpdateUserRequest;
import com.zevra.zevra.entities.User;
import com.zevra.zevra.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> user = userService.getUserById(id);

        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User userCreated = userService.createUser(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable UUID id,@Valid @RequestBody UpdateUserRequest request) {
        try{
            Optional<User> user = userService.updateUser(id, request);

            if(user.isPresent()){
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        Optional<User> deletedUser = userService.deleteUser(id);

        if(deletedUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
