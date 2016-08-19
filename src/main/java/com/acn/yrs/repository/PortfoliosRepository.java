package com.acn.yrs.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.Portfolio;

@RepositoryRestResource(path="portfolios", exported=false)
public interface PortfoliosRepository extends PagingAndSortingRepository<Portfolio, Integer>{


	public List<Portfolio> findPortfolioByAssessmentId(Integer assessmentId);
}
