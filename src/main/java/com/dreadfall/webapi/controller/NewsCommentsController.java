package com.dreadfall.webapi.controller;

import com.dreadfall.webapi.model.dto.NewsCommentDto;
import com.dreadfall.webapi.service.NewsCommentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/comments")
public class NewsCommentsController {

    private final NewsCommentsService newsCommentsService;

    public NewsCommentsController(NewsCommentsService newsCommentsService) {
        this.newsCommentsService = newsCommentsService;
    }

    @GetMapping
    public List<NewsCommentDto> findAllByNewsId(@RequestParam(required = true) Long newsId) {
        return newsCommentsService.findAllByNewsId(newsId);
    }
}
