package com.piinalpin.queryrequest.service;

import com.piinalpin.queryrequest.domain.common.query.SearchRequest;
import com.piinalpin.queryrequest.domain.common.query.SearchSpecification;
import com.piinalpin.queryrequest.domain.dao.OperatingSystem;
import com.piinalpin.queryrequest.repository.OperatingSystemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OperatingSystemService {

    @Autowired
    private OperatingSystemRepository operatingSystemRepository;

    public Page<OperatingSystem> searchOperatingSystem(SearchRequest request) {
        SearchSpecification<OperatingSystem> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return operatingSystemRepository.findAll(specification, pageable);
    }

}
