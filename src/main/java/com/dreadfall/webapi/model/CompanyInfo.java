package com.dreadfall.webapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMPANY_INFO")
public class CompanyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String shortDescription;
    private String address;
    private String email;
    private String phone;
    private String fax;
    private String facebookUrl;
    private String twitterUrl;
    private String instagramUrl;
    private String linkedInUrl;
    private String githubUrl;
}
