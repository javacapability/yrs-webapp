package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.LiabilityType;

@RepositoryRestResource(path="liabilitytypes")
public interface LiabilityTypesRepository extends PagingAndSortingRepository<LiabilityType, Integer>{


}
