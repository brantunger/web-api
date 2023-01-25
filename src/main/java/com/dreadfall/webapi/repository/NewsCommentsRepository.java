package com.dreadfall.webapi.repository;

import com.dreadfall.webapi.model.NewsComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsCommentsRepository extends CrudRepository<NewsComment, Long> {

    List<NewsComment> findAllByNewsId(Long newsId);
}
