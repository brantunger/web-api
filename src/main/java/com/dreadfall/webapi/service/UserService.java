package com.dreadfall.webapi.service;

import com.dreadfall.webapi.exception.DuplicateUsernameException;
import com.dreadfall.webapi.model.User;
import com.dreadfall.webapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getUserId());

        if (userOptional.isPresent()) {
            User originalUser = userOptional.get();
            user.setPassword(originalUser.getPassword());
        }
        return userRepository.save(user);
    }

    public User save(User user) throws DuplicateUsernameException {
        if (userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()) != null)
            throw new DuplicateUsernameException("Duplicate Username or Email");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(long userId) {
        userRepository.deleteByUserId(userId);
    }
}
