package com.dreadfallguild.webapi.service;

import com.dreadfallguild.webapi.model.News;
import com.dreadfallguild.webapi.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> findAllOrderByDateCreatedDesc() {
        return newsRepository.findAllOrderByDateCreatedDesc();
    }

    public News updateNews(Long newsId, News newsRequest) {
        final Optional<News> news = newsRepository.findById(newsId);
        if (!news.isPresent()) {
            throw new RuntimeException(String.format("News story with id: %d doesn't exist!", newsId));
        }

        news.get().setTitle(newsRequest.getTitle());
        news.get().setContent(newsRequest.getContent());
        news.get().setVotes(newsRequest.getVotes());

        return newsRepository.save(news.get());
    }
}
