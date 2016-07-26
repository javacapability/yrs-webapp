package com.acn.yrs.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.Questionnaire;

@RepositoryRestResource(path="questionnaires", exported=false)
public interface QuestionnairesRepository extends PagingAndSortingRepository<Questionnaire, Integer>{

	List<Questionnaire> findQuestionnaireByAssessmentId(int assessmentId);


}
