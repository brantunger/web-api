package com.dreadfall.webapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NEWS")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NEWS_ID")
    private Long newsId;

    @NotBlank
    @Column(name = "TITLE")
    private String title;

    @NotBlank
    @Column(name = "CONTENT")
    private String content;

    @NotNull
    @Column(name = "VOTES")
    private Long votes = 0L;

    @NotBlank
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "DATE_CREATED")
    private Date dateCreated;
}
