package com.dreadfall.webapi.repository;

import com.dreadfall.webapi.model.CompanyInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyInfoRepository extends CrudRepository<CompanyInfo, Integer> {

    @Query(value = "SELECT * FROM company_info LIMIT 1", nativeQuery = true)
    CompanyInfo findAllCompanyInfo();
}
