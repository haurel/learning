package com.aurel.service;

import com.aurel.controller.request.UserSignupRequest;
import com.aurel.model.User;
import com.aurel.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
    }


    @Test
    void signup() {
        UserSignupRequest userTest = new UserSignupRequest(
                "aurel@gmail.com",
                "aaa",
                "Aurel",
                "Hila",
                "071122122"
        );
        User user = new User(
                userTest.getEmail(),
                userTest.getFirstName(),
                userTest.getLastName(),
                userTest.getMobileNumber(),
                userTest.getPassword(),
                "0"
        );

        Mockito.when(userRepository.save(user)).thenReturn(user);

        assertEquals(user, userService.signup(user));
    }

    @Test
    void getAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(
                Stream.of(
                        new User(
                                "aurel@gmail.com",
                                "aaa",
                                "Aurel",
                                "Hila",
                                "071122122",
                                "0"
                        ),
                        new User(
                                "aurel@gmail.com",
                                "aaa",
                                "Aurel",
                                "Hila",
                                "071122122",
                                "0"
                        )
                ).collect(Collectors.toList()));

        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    void getUserById() {
        User user = new User(
                "aurel@gmail.com",
                "aaa",
                "Aurel",
                "Hila",
                "071122122",
                "0");

        userService.signup(user);
        userService.getUserById("1");

        verify(userRepository, times(1)).findById("1");
    }
}