package com.dreadfall.webapi.service;

import com.dreadfall.webapi.exception.DuplicateUsernameException;
import com.dreadfall.webapi.model.User;
import com.dreadfall.webapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> user.setPassword("PROTECTED"));
        return users;
    }

    public User save(User user) throws DuplicateUsernameException {
        if (userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()) != null)
            throw new DuplicateUsernameException("Duplicate Username or Email");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
