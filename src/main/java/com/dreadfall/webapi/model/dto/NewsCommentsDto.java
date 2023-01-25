package com.dreadfall.webapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsCommentsDto {

    private Long commentId;

    private Long newsId;

    private Long parentId;

    private String content;

    private String createdBy;

    private Date dateCreated;

    private List<NewsCommentsDto> comments;
}
