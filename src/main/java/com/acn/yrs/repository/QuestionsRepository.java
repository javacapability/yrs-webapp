package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.Question;

@RepositoryRestResource(path="questions")
public interface QuestionsRepository extends PagingAndSortingRepository<Question, Integer>{


}
