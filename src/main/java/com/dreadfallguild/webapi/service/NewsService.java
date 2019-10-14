package com.dreadfallguild.webapi.service;

import com.dreadfallguild.webapi.model.News;
import com.dreadfallguild.webapi.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> findAllOrderByDateCreatedDesc() {
        return newsRepository.findAllOrderByDateCreatedDesc();
    }
}
