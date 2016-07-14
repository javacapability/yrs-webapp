package com.acn.yrs.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger LOG = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Override
	public Question create(Question questionInfo) {
		
				LOG.info("Create Question Service()");
				Question questionDB = questionsRepository.findByQuestion(questionInfo.getQuestion());
				if (questionDB == null) {
					questionsRepository.save(questionInfo);
				}
				return questionInfo;
	}

	@Override
	public Question update(Question questionInfo) {
		LOG.info("Update Question Service()");
		Question questionDB = questionsRepository.findOne(questionInfo.getId());
		
		if(questionDB != null){

			questionDB.setQuestion(questionInfo.getQuestion());
			questionDB.setAnswerTypes(questionInfo.getAnswerTypes());
			questionDB.setFalseWeight(questionInfo.getFalseWeight());
			questionDB.setIsActive(questionInfo.getIsActive());
			questionDB.setNoWeight(questionInfo.getNoWeight());
			questionDB.setPriorityNumber(questionInfo.getPriorityNumber());
			questionDB.setTrueWeight(questionInfo.getTrueWeight());
			questionDB.setYesWeight(questionInfo.getYesWeight());
			questionDB.setAnswers(questionInfo.getAnswers());
		    questionsRepository.save(questionDB);
		}
		 
		return questionDB;
	}

	@Override
	public void delete(int questionId) {
		LOG.info("Delete User Service()");
		Question questionDB = questionsRepository.findOne(questionId);
		if (questionDB != null) {
			questionsRepository.delete(questionDB);
		}
	}


}
