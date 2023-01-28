package com.dreadfall.webapi.controller;

import com.dreadfall.webapi.model.News;
import com.dreadfall.webapi.model.dto.NewsCommentDto;
import com.dreadfall.webapi.service.NewsCommentsService;
import com.dreadfall.webapi.service.NewsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/news")
public class NewsController {

    private final NewsService newsService;
    private final NewsCommentsService newsCommentsService;

    public NewsController(NewsService newsService,
                          NewsCommentsService newsCommentsService) {
        this.newsService = newsService;
        this.newsCommentsService = newsCommentsService;
    }

    @GetMapping
    public List<News> findAllOrderByDateCreatedDesc() {
        return newsService.findAllOrderByDateCreatedDesc();
    }

    @GetMapping("/{id}")
    public News findById(@PathVariable("id") Long id) {
        return newsService.findById(id);
    }

    @PostMapping
    public News addNews(@RequestBody @Valid News news) {
        return newsService.addNews(news);
    }

    @PutMapping("/{id}")
    public News updateNews(@PathVariable Long id, @RequestBody @Valid News news) {
        return newsService.updateNews(id, news);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
    }

    // TODO: Possibly refactor to use above put mapping?
    @PutMapping
    public News updateVoteCount(@RequestParam("id") Long id, @RequestParam("voteCount") Long voteCount) {
        return newsService.updateVoteCount(id, voteCount);
    }

    @GetMapping("/{newsId}/comments")
    public List<NewsCommentDto> findAllCommentsByNewsId(@PathVariable("newsId") Long newsId) {
        return newsCommentsService.findAllByNewsId(newsId);
    }

    @PostMapping("/{newsId}/comments")
    public NewsCommentDto addComment(@PathVariable("newsId") Long newsId,
                                     @RequestBody @Valid NewsCommentDto newsCommentDto) {
        return newsCommentsService.addComment(newsId, newsCommentDto);
    }

    @DeleteMapping("/{newsId}/comments/{commentId}")
    public List<NewsCommentDto> deleteByNewsIdAndCommentIds(@PathVariable("newsId") Long newsId,
                                                            @PathVariable("commentId") Long commentId) {
        return newsCommentsService.deleteByNewsIdAndCommentIds(newsId, commentId);
    }
}
