package com.dreadfall.webapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "SHOUT_MESSAGE")
@EntityListeners(AuditingEntityListener.class)
public class ShoutMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @NotBlank
    private String username;

    @NotBlank
    private String message;

    @CreatedDate
    private Date dateCreated;
}
