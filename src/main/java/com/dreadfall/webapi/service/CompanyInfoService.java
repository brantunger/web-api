package com.dreadfall.webapi.service;

import com.dreadfall.webapi.model.CompanyInfo;
import com.dreadfall.webapi.repository.CompanyInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyInfoService {

    private final CompanyInfoRepository companyInfoRepository;

    public CompanyInfoService(CompanyInfoRepository companyInfoRepository) {
        this.companyInfoRepository = companyInfoRepository;
    }

    public CompanyInfo getCompanyInfo() {
        return companyInfoRepository.findAllCompanyInfo();
    }
}
