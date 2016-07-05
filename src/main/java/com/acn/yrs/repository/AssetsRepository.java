package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.Asset;

@RepositoryRestResource(path="assets")
public interface AssetsRepository extends PagingAndSortingRepository<Asset, Integer>{

}
