package com.dreadfall.webapi.service;

import com.dreadfall.webapi.model.User;
import com.dreadfall.webapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(x -> x.setPassword("PROTECTED"));
        return users;
    }
}
