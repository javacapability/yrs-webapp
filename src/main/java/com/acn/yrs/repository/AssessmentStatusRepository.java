package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.AssessmentStatus;

@RepositoryRestResource(path="assessmentstatus")
public interface AssessmentStatusRepository extends PagingAndSortingRepository<AssessmentStatus, Integer>{


}
