package com.dreadfallguild.webapi.repository;

import com.dreadfallguild.webapi.model.ShoutMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoutMessageRepository extends CrudRepository<ShoutMessage, Long> {
    List<ShoutMessage> findAll();
}
