package com.dreadfall.webapi.repository;

import com.dreadfall.webapi.model.NewsComments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsCommentsRepository extends CrudRepository<NewsComments, Long> {

    List<NewsComments> findAllByNewsId(Long newsId);
}
