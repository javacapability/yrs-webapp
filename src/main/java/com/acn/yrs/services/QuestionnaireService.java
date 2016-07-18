package com.acn.yrs.services;

import java.util.List;

import com.acn.yrs.models.Question;
import com.acn.yrs.models.Questionnaire;

public interface QuestionnaireService {

	List<Questionnaire> findQuestionnaireByAssessmentId(int assessmentId);

}
