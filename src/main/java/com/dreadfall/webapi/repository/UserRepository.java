package com.dreadfall.webapi.repository;

import com.dreadfall.webapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @NonNull List<User> findAll();

    User findByUsername(String username);

    User findByUsernameOrEmail(String username, String email);

    void deleteByUserId(@NonNull Long userId);
}
