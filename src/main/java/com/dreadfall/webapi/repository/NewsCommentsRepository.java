package com.dreadfall.webapi.repository;

import com.dreadfall.webapi.model.NewsComment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsCommentsRepository extends CrudRepository<NewsComment, Long> {

    List<NewsComment> findAllByNewsId(Long newsId);

    @Modifying
    @Query(value = """
            DELETE
            FROM news_comments nc
            WHERE nc.NEWS_ID = :newsId
              AND nc.COMMENT_ID IN (:commentIds)""", nativeQuery = true)
    void deleteByNewsIdAndCommentIds(@Param("newsId") Long newsId, @Param("commentIds") List<Long> commentIds);

}
