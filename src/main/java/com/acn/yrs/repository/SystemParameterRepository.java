package com.acn.yrs.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.Question;
import com.acn.yrs.models.SystemParameter;

@RepositoryRestResource(path="syspars")
public interface SystemParameterRepository extends PagingAndSortingRepository<SystemParameter, Integer> {
         
	public List<SystemParameter> findAllByActive(int active);
}
