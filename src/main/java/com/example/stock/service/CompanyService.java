package com.example.stock.service;

import com.example.stock.model.Company;
import com.example.stock.persist.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {
    Company save(String ticker);

    Company storeCompanyAndDividend(String ticker);

    Page<CompanyEntity> getAllCompany(Pageable pageable);

    //자동 완성 Like 추가
    List<String> getCompanyNamesByKeyword(String keyword);

    //자동 완성 추가
    void addAutocompleteKeyword(String keyword);

    //자동 완성 조회
    List<String> autocomplete(String keyword);

    String deleteCompany(String ticker);
}
