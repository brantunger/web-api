package com.dreadfall.webapi.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsCommentDto {

    private Long commentId;

    private Long newsId;

    private Long parentId;

    @NotBlank
    private String content;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Date dateCreated;

    @Builder.Default
    private List<NewsCommentDto> comments = new ArrayList<>();
}
