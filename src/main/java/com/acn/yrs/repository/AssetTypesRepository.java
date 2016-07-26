package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.Asset;
import com.acn.yrs.models.AssetType;

@RepositoryRestResource(path="assettypes")
public interface AssetTypesRepository extends PagingAndSortingRepository<AssetType, Integer>{

}
