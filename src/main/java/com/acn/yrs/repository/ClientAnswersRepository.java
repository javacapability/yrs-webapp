package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.ClientAnswer;

@RepositoryRestResource(path="clientanswers")//, exported=false)
public interface ClientAnswersRepository extends PagingAndSortingRepository<ClientAnswer, Integer>{


}
