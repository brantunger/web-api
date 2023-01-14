package com.dreadfall.webapi.controller;

import com.dreadfall.webapi.model.CompanyInfo;
import com.dreadfall.webapi.service.CompanyInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/companyinfo")
public class CompanyInfoController {

    private final CompanyInfoService companyInfoService;

    public CompanyInfoController(CompanyInfoService companyInfoService) {
        this.companyInfoService = companyInfoService;
    }

    @GetMapping
    public CompanyInfo getCompanyInfo() {
        return companyInfoService.getCompanyInfo();
    }
}
