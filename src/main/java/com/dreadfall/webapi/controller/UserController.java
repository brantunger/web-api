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

    @PatchMapping
    public User updateUser(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody @Valid User user) throws DuplicateUsernameException {
        return userService.save(user);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        final String token = authenticationService.authenticate(authenticationRequest);
        return new AuthenticationResponse(token, authenticationRequest.getUsername());
    }
}
