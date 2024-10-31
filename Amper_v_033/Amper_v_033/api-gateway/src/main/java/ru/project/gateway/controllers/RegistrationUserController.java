package ru.project.gateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.security.models.UserDto;
import ru.project.security.services.UserService;

@RestController
@RequestMapping("/registration")
public class RegistrationUserController {

    private final UserService userService;
    @Autowired
    public RegistrationUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto creation(@RequestBody UserDto userDto) {
        return userService.CreateUser(userDto);
    }
}