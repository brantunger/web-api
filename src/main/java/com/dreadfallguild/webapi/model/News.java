package com.dreadfallguild.webapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty
    @NotBlank
    @Column(name = "TITLE")
    private String title;

    @NotEmpty
    @NotBlank
    @Column(name = "CONTENT")
    private String content;

    @NotNull
    @Column(name = "VOTES")
    private Long votes;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;
}
