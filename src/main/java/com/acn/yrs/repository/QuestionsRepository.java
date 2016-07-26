package com.acn.yrs.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.Question;

@RepositoryRestResource(path="questions", exported=false)
public interface QuestionsRepository extends PagingAndSortingRepository<Question, Integer>{


	List<Question> findAllByIsActive(int isActive);
    Question findByQuestionTxt(String questionTxt);

}
