package com.acn.yrs.repository;

import com.acn.yrs.models.Answer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="answers", exported=false)
public interface AnswersRepository extends PagingAndSortingRepository<Answer, Integer>{


}
