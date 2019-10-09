package com.wildangelsguild.webapi.repository;

import com.wildangelsguild.webapi.model.ShoutMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoutMessageRepository extends CrudRepository<ShoutMessage, Long> {
    List<ShoutMessage> findAll();
}
