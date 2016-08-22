package com.acn.yrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.Question;
import com.acn.yrs.models.Questionnaire;

@RepositoryRestResource(path="questionnaires", exported=false)
public interface QuestionnairesRepository extends PagingAndSortingRepository<Questionnaire, Integer>{

	String Q_GET_QUESTIONNAIRE = "select qn, q from Questionnaire qn right join qn.question q on qn.assessmentId = ?1";

	//String Q_GET_QUESTIONNAIRE = "select qn from Questionnaire qn right join qn.question q where qn.assessmentId = ?1";
	//SELECT * FROM your_risk_snapshot.questionnaire qn right join your_risk_snapshot.questions q on q.id=qn.questionid and qn.assessmentid=3;
	//List<Questionnaire> findQuestionnaireByAssessmentId(int assessmentId);

	@Query(Q_GET_QUESTIONNAIRE)
	List<Questionnaire> findQuestionnaireByAssessmentId(int assessmentId);


}
