package com.example.stock.service;

import com.example.stock.exception.impl.NoCompanyException;
import com.example.stock.model.Company;
import com.example.stock.model.ScrapedResult;
import com.example.stock.persist.CompanyRepository;
import com.example.stock.persist.DividendRepository;
import com.example.stock.persist.entity.CompanyEntity;
import com.example.stock.persist.entity.DividendEntity;
import com.example.stock.scraper.Scraper;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.Trie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    //자동 완성 삭제
    void deleteAutoCompleteKeyword(String keyword);

    String deleteCompany(String ticker);
}
