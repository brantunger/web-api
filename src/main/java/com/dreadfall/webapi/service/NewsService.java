package com.dreadfall.webapi.service;

import com.dreadfall.webapi.model.News;
import com.dreadfall.webapi.repository.NewsCommentsRepository;
import com.dreadfall.webapi.repository.NewsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final NewsCommentsRepository newsCommentsRepository;

    public NewsService(NewsRepository newsRepository, NewsCommentsRepository newsCommentsRepository) {
        this.newsRepository = newsRepository;
        this.newsCommentsRepository = newsCommentsRepository;
    }

    public News findById(Long id) {
        final Optional<News> news = newsRepository.findById(id);
        if (news.isEmpty()) {
            throw new EntityNotFoundException(String.format("News story with id: %d doesn't exist!", id));
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
        if (news.isEmpty()) {
            throw new EntityNotFoundException(String.format("News story with id: %d doesn't exist!", id));
        }

        news.get().setTitle(newsRequest.getTitle());
        news.get().setContent(newsRequest.getContent());
        news.get().setVotes(newsRequest.getVotes());

        return newsRepository.save(news.get());
    }

    @Transactional
    public void deleteNews(Long newsId) {
        newsRepository.deleteByNewsId(newsId);
        newsCommentsRepository.deleteByNewsId(newsId);
    }

    public News updateVoteCount(Long id, Long voteCount) {
        final Optional<News> news = newsRepository.findById(id);
        if (news.isEmpty()) {
            throw new RuntimeException(String.format("News story with id: %d doesn't exist!", id));
        }

        news.get().setVotes(voteCount);

        return newsRepository.save(news.get());
    }
}
