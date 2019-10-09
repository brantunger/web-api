package com.wildangelsguild.webapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "SHOUT_MESSAGE")
public class ShoutMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private Long messageId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;
}
