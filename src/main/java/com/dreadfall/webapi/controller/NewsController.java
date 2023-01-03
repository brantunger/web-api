package com.dreadfall.webapi.controller;

import com.dreadfall.webapi.model.News;
import com.dreadfall.webapi.service.NewsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
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

    // TODO: Possibly refactor to use above put mapping?
    @PutMapping()
    public News updateVoteCount(@RequestParam("id") Long id, @RequestParam("voteCount") Long voteCount) {
        return newsService.updateVoteCount(id, voteCount);
    }
}
