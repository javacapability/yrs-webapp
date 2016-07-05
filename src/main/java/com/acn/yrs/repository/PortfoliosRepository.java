package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.Portfolio;

@RepositoryRestResource(path="portfolios")
public interface PortfoliosRepository extends PagingAndSortingRepository<Portfolio, Integer>{


}
