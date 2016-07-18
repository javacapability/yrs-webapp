package com.acn.yrs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.BaseConstants;
import com.acn.yrs.models.Questionnaire;
import com.acn.yrs.repository.QuestionnairesRepository;

@Service("questionnaireService")
@Transactional
public class QuestionnaireServiceImpl extends BaseConstants implements QuestionnaireService {

	@Autowired
	QuestionnairesRepository questionnaireRepository;

	/**
	 * @return the questionnaireRepository
	 */
	public QuestionnairesRepository getQuestionnaireRepository() {
		return questionnaireRepository;
	}

	/**
	 * @param questionnaireRepository the questionnaireRepository to set
	 */
	public void setQuestionnaireRepository(
			QuestionnairesRepository questionnaireRepository) {
		this.questionnaireRepository = questionnaireRepository;
	}

	@Override
	public List<Questionnaire> findQuestionnaireByAssessmentId(int assessmentId) {
		// TODO Auto-generated method stub
		return questionnaireRepository.findQuestionnaireByAssessmentId(assessmentId);
	}
}


