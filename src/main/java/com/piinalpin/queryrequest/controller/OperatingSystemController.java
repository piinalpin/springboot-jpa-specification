package com.piinalpin.queryrequest.controller;

import com.piinalpin.queryrequest.domain.common.query.SearchRequest;
import com.piinalpin.queryrequest.domain.dao.OperatingSystem;
import com.piinalpin.queryrequest.service.OperatingSystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/operating-system", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OperatingSystemController {

    @Autowired
    private OperatingSystemService operatingSystemService;

    @PostMapping(value = "/search")
    public Page<OperatingSystem> search(@RequestBody SearchRequest request) {
        return operatingSystemService.searchOperatingSystem(request);
    }

}
