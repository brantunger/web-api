package com.dreadfall.webapi.repository;

import com.dreadfall.webapi.model.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {

    void deleteByNewsId(@NonNull Long newsId);

    @Query(value = "SELECT * FROM news ORDER BY DATE_CREATED DESC", nativeQuery = true)
    List<News> findAllOrderByDateCreatedDesc();
}
