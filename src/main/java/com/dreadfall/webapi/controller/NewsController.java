package com.dreadfall.webapi.controller;

import com.dreadfall.webapi.controller.validation.ValidationUtils;
import com.dreadfall.webapi.model.News;
import com.dreadfall.webapi.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<List<News>> getAllNews() {
        return new ResponseEntity<>(newsService.findAllOrderByDateCreatedDesc(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody @Valid News news, BindingResult bindingResult) {
        ValidationUtils.processErrors(bindingResult);
        return new ResponseEntity<>(newsService.updateNews(id, news), HttpStatus.ACCEPTED);
    }
}
