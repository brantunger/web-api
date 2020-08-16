package com.dreadfall.webapi.controller;

import com.dreadfall.webapi.model.News;
import com.dreadfall.webapi.service.NewsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/news", produces = "application/json")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public @ResponseBody List<News> getAllNews() {
        return newsService.findAllOrderByDateCreatedDesc();
    }

    @PutMapping("/{id}")
    public @ResponseBody News updateNews(@PathVariable Long id, @RequestBody @Valid News news) {
        return newsService.updateNews(id, news);
    }

    @PutMapping()
    public @ResponseBody News updateVoteCount(@RequestParam(name = "id") Long id, @RequestParam(name = "voteCount") Long voteCount) {
        return newsService.updateVoteCount(id, voteCount);
    }
}
