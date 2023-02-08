package com.dreadfall.webapi.controller;

import com.dreadfall.webapi.exception.DuplicateUsernameException;
import com.dreadfall.webapi.model.User;
import com.dreadfall.webapi.request.AuthenticationRequest;
import com.dreadfall.webapi.response.AuthenticationResponse;
import com.dreadfall.webapi.service.AuthenticationService;
import com.dreadfall.webapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping ("/{id}")
    public User updateUser(@PathVariable("id") Long userId, @Valid @RequestBody User user) throws DuplicateUsernameException {
        return userService.updateUser(user);
    }

    @PostMapping(value = "/register")
    public User registerUser(@RequestBody @Valid User user) throws DuplicateUsernameException {
        return userService.save(user);
    }

    @PostMapping(value = "/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        final String token = authenticationService.authenticate(authenticationRequest);
        return new AuthenticationResponse(token, authenticationRequest.getUsername());
    }

}
