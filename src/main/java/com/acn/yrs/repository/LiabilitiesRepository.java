package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.Liability;

@RepositoryRestResource(path="liabilities", exported=false)
public interface LiabilitiesRepository extends PagingAndSortingRepository<Liability, Integer>{


}
