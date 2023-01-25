package com.dreadfall.webapi.service;

import com.dreadfall.webapi.model.NewsComments;
import com.dreadfall.webapi.model.dto.NewsCommentsDto;
import com.dreadfall.webapi.repository.NewsCommentsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsCommentsService {

    private final NewsCommentsRepository newsCommentsRepository;

    public NewsCommentsService(NewsCommentsRepository newsCommentsRepository) {
        this.newsCommentsRepository = newsCommentsRepository;
    }

    public List<NewsCommentsDto> findAllByNewsId(Long newsId) {
        List<NewsComments> unalteredComments = newsCommentsRepository.findAllByNewsId(newsId);
        List<NewsCommentsDto> comments = new ArrayList<>();

        unalteredComments.forEach(e -> {

        });
        return comments;
    }
}