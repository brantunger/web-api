package com.dreadfallguild.webapi.controller;

import com.dreadfallguild.webapi.model.News;
import com.dreadfallguild.webapi.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/news", produces = "application/json")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        return new ResponseEntity<>(newsService.findAllOrderByDateCreatedDesc(), HttpStatus.OK);
    }
}
