package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.RiskAssessment;

@RepositoryRestResource(path="riskassessments")
public interface RiskAssessmentsRepository extends PagingAndSortingRepository<RiskAssessment, Integer>{


}
