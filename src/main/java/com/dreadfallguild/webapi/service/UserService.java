package com.dreadfallguild.webapi.service;

import com.dreadfallguild.webapi.model.User;
import com.dreadfallguild.webapi.repository.UserRepository;
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
        users.stream().forEach(x -> x.setPassword("PROTECTED"));
        return users;
    }
}
