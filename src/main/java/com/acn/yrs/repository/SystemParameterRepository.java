package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.SystemParameter;

@RepositoryRestResource(path="syspars")
public interface SystemParameterRepository extends PagingAndSortingRepository<SystemParameter, Integer> {

}
