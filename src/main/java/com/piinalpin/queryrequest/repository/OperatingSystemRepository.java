package com.piinalpin.queryrequest.repository;

import com.piinalpin.queryrequest.domain.dao.OperatingSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatingSystemRepository extends JpaRepository<OperatingSystem, Long>,
        JpaSpecificationExecutor<OperatingSystem> {
}
