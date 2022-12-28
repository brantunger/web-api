package com.dreadfall.webapi.service;

import com.dreadfall.webapi.exception.UnauthorizedException;
import com.dreadfall.webapi.model.User;
import com.dreadfall.webapi.request.AuthenticationRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenService jwtTokenService;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 JwtUserDetailsService jwtUserDetailsService,
                                 JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenService = jwtTokenService;
    }

    public String authenticate(AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException ex) {
            throw new UnauthorizedException("Incorrect User/Password");
        }

        final User userDetails = (User) jwtUserDetailsService.loadUserByUsername(username);

        return jwtTokenService.generateToken(userDetails);
    }
}
