package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.AuditLog;

@RepositoryRestResource(path="auditlogs")
public interface AuditLogsRepository extends PagingAndSortingRepository<AuditLog, Integer>{


}
