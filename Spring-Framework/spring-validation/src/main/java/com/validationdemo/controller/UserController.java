package com.validationdemo.controller;

import com.validationdemo.exception.UserNotFoundException;
import com.validationdemo.model.User;
import com.validationdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable (value = "id") long id){
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        this.userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable( value = "id") long id){
        User foundUser = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        foundUser.setName(user.getName());
        foundUser.setEmail(user.getEmail());

        return this.userRepository.save(foundUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long id){
        User foundUser = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        this.userRepository.delete(foundUser);

        return ResponseEntity.ok().build();
    }




}
