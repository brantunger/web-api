package com.dreadfall.webapi.service;

import com.dreadfall.webapi.model.News;
import com.dreadfall.webapi.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public News findById(Long id) {
        final Optional<News> news = newsRepository.findById(id);
        if (!news.isPresent()) {
            throw new RuntimeException(String.format("News story with id: %d doesn't exist!", id));
        }

        return news.get();
    }

    public List<News> findAllOrderByDateCreatedDesc() {
        return newsRepository.findAllOrderByDateCreatedDesc();
    }

    public News addNews(News news) {
        return newsRepository.save(news);
    }

    public News updateNews(Long id, News newsRequest) {
        final Optional<News> news = newsRepository.findById(id);
        if (!news.isPresent()) {
            throw new RuntimeException(String.format("News story with id: %d doesn't exist!", id));
        }

        news.get().setTitle(newsRequest.getTitle());
        news.get().setContent(newsRequest.getContent());
        news.get().setVotes(newsRequest.getVotes());

        return newsRepository.save(news.get());
    }

    public News updateVoteCount(Long id, Long voteCount) {
        final Optional<News> news = newsRepository.findById(id);
        if (!news.isPresent()) {
            throw new RuntimeException(String.format("News story with id: %d doesn't exist!", id));
        }

        news.get().setVotes(voteCount);

        return newsRepository.save(news.get());
    }
}
