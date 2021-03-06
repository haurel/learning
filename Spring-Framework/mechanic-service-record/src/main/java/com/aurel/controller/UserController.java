package com.aurel.controller;

import com.aurel.controller.request.UserSignupRequest;
import com.aurel.model.User;
import com.aurel.repository.UserRepository;
import com.aurel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @PostMapping("/signup")
    public User signup(@RequestBody UserSignupRequest userSignupRequest){
        User user = new User(
                userSignupRequest.getEmail(),
                userSignupRequest.getFirstName(),
                userSignupRequest.getLastName(),
                userSignupRequest.getMobileNumber(),
                userSignupRequest.getPassword(),
                "0"
        );
        return userService.signup(user);
    }





}
