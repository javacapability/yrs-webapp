package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.Questionnaire;

@RepositoryRestResource(path="questionnaires")
public interface QuestionnairesRepository extends PagingAndSortingRepository<Questionnaire, Integer>{


}
