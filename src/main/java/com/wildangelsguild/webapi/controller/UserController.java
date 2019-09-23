package com.wildangelsguild.webapi.controller;

import com.wildangelsguild.webapi.component.JwtTokenUtil;
import com.wildangelsguild.webapi.controller.validation.ValidationUtils;
import com.wildangelsguild.webapi.model.User;
import com.wildangelsguild.webapi.request.JwtRequest;
import com.wildangelsguild.webapi.response.JwtResponse;
import com.wildangelsguild.webapi.service.JwtUserDetailsService;
import com.wildangelsguild.webapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/user", produces="application/json")
public class UserController {
    private final UserService userService;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public UserController(UserService userService,
                          JwtUserDetailsService jwtUserDetailsService,
                          AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody @Valid JwtRequest jwtRequest, BindingResult bindingResult) throws Exception {
        ValidationUtils.processErrors(bindingResult);
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        ValidationUtils.processErrors(bindingResult);
        return new ResponseEntity<>(jwtUserDetailsService.save(user), HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
