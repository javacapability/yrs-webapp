package com.acn.yrs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.BaseConstants;
import com.acn.yrs.models.Question;
import com.acn.yrs.repository.QuestionsRepository;

@Service("questionService")
@Transactional
public class QuestionServiceImpl extends BaseConstants implements QuestionService {

	@Autowired
	QuestionsRepository questionsRepository;

	@Override
	public List<Question> getQuestionList() {
		// TODO Auto-generated method stub
		return questionsRepository.findAllByIsActive(1);
	}

	/**
	 * @return the questionsRepository
	 */
	public QuestionsRepository getQuestionsRepository() {
		return questionsRepository;
	}

	/**
	 * @param questionsRepository the questionsRepository to set
	 */
	public void setQuestionsRepository(QuestionsRepository questionsRepository) {
		this.questionsRepository = questionsRepository;
	}

}
