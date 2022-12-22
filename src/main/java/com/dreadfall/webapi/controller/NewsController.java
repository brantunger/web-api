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
    public @ResponseBody List<News> findAllOrderByDateCreatedDesc() {
        return newsService.findAllOrderByDateCreatedDesc();
    }

    @GetMapping("{/id}")
    public @ResponseBody News findById(@RequestParam("id") Long id) {
        return newsService.findById(id);
    }

    @PostMapping
    public @ResponseBody News addNews(@RequestBody @Valid News news) {
        return newsService.addNews(news);
    }

    @PutMapping("/{id}")
    public @ResponseBody News updateNews(@PathVariable Long id, @RequestBody @Valid News news) {
        return newsService.updateNews(id, news);
    }

    // TODO: Possibly refactor to use above put mapping?
    @PutMapping()
    public @ResponseBody News updateVoteCount(@RequestParam(name = "id") Long id, @RequestParam(name = "voteCount") Long voteCount) {
        return newsService.updateVoteCount(id, voteCount);
    }
}
