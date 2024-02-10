package com.example.email_verification.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping
    public String startPage(){
        return "Welcome!";
    }
}
