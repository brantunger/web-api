package com.dreadfall.webapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NEWS_COMMENTS")
@EntityListeners(AuditingEntityListener.class)
public class NewsComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotBlank
    private Long newsId;

    private Long parentId;

    @NotBlank
    private String content;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Date dateCreated;
}
