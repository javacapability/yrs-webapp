package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.AnswerType;

@RepositoryRestResource(path="answertypes")
public interface AnswerTypesRepository extends PagingAndSortingRepository<AnswerType, Integer>{


}
