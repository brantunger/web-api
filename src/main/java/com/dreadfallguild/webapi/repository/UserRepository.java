package com.dreadfallguild.webapi.repository;

import com.dreadfallguild.webapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByUsername(String username);
    User findByUsernameOrEmail(String username, String email);
}
