package com.dreadfall.webapi.controller;

import com.dreadfall.webapi.model.User;
import com.dreadfall.webapi.request.JwtRequest;
import com.dreadfall.webapi.response.JwtResponse;
import com.dreadfall.webapi.service.JwtUserDetailsService;
import com.dreadfall.webapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/user", produces = "application/json")
public class UserController {
    private final UserService userService;
    private final JwtUserDetailsService jwtUserDetailsService;

    public UserController(UserService userService, JwtUserDetailsService jwtUserDetailsService) {
        this.userService = userService;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @GetMapping
    public @ResponseBody List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/register")
    public @ResponseBody User registerUser(@RequestBody @Valid User user) {
        return jwtUserDetailsService.save(user);
    }

    @PostMapping(value = "/authenticate")
    public @ResponseBody JwtResponse createAuthenticationToken(@RequestBody @Valid JwtRequest jwtRequest) {
        final String token = jwtUserDetailsService.createAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword());
        return new JwtResponse(token, jwtRequest.getUsername());
    }

}
