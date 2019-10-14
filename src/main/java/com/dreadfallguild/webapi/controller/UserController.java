package com.dreadfallguild.webapi.controller;

import com.dreadfallguild.webapi.controller.validation.ValidationUtils;
import com.dreadfallguild.webapi.model.User;
import com.dreadfallguild.webapi.request.JwtRequest;
import com.dreadfallguild.webapi.response.JwtResponse;
import com.dreadfallguild.webapi.service.JwtUserDetailsService;
import com.dreadfallguild.webapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        ValidationUtils.processErrors(bindingResult);
        return new ResponseEntity<>(jwtUserDetailsService.save(user), HttpStatus.OK);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody @Valid JwtRequest jwtRequest, BindingResult bindingResult) {
        ValidationUtils.processErrors(bindingResult);
        final String token = jwtUserDetailsService.createAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword());
        return new ResponseEntity<>(new JwtResponse(token, jwtRequest.getUsername()), HttpStatus.OK);
    }

}
