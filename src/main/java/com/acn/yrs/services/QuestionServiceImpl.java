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
public class QuestionServiceImpl extends BaseConstants implements
		QuestionService {

	@Autowired
	QuestionsRepository questionsRepository;

	@Autowired
	AuditLogService auditLogService;

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
	 * @param questionsRepository
	 *            the questionsRepository to set
	 */
	public void setQuestionsRepository(QuestionsRepository questionsRepository) {
		this.questionsRepository = questionsRepository;
	}

	Logger LOG = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Override
	public Question create(Question questionInfo) {

		LOG.info("Create Question Service()");
		Question questionDB = questionsRepository
				.findByQuestionTxt(questionInfo.getQuestionTxt());
		if (questionDB == null) {
			questionsRepository.save(questionInfo);
			questionInfo.postSaveOrUpdate();
			auditLogService.saveTransaction(questionInfo.getAuditLog(),
					SAVE_ACTION, AUDIT_TXN_SUCCESS, TXN_CREATE_QUESTION);
		}
		return questionInfo;
	}

	@Override
	public Question update(Question questionInfo) {
		LOG.info("Update Question Service()");
		Question questionDB = questionsRepository.findOne(questionInfo.getId());

		questionDB.preSaveOrUpdate();

		if (questionDB != null) {

			questionDB.setQuestionTxt(questionInfo.getQuestionTxt());
			questionDB.setAnswerTypes(questionInfo.getAnswerTypes());
			questionDB.setFalseWeight(questionInfo.getFalseWeight());
			questionDB.setIsActive(questionInfo.getIsActive());
			questionDB.setNoWeight(questionInfo.getNoWeight());
			questionDB.setPriorityNumber(questionInfo.getPriorityNumber());
			questionDB.setTrueWeight(questionInfo.getTrueWeight());
			questionDB.setYesWeight(questionInfo.getYesWeight());
			questionDB.setAnswers(questionInfo.getAnswers());
			questionDB.postSaveOrUpdate();
			questionsRepository.save(questionDB);



			auditLogService.saveTransaction(questionDB.getAuditLog(),
					UPDATE_ACTION, AUDIT_TXN_SUCCESS, TXN_UPDATE_QUESTION);
		}

		return questionDB;
	}

	@Override
	public Question getQuestion(int questionId) {
		LOG.info("Get Question Service()");
		Question questionDB = questionsRepository.findOne(questionId);


		return questionDB;
	}

	@Override
	public void delete(int questionId) {
		LOG.info("Delete User Service()");
		Question questionDB = questionsRepository.findOne(questionId);
		questionDB.preSaveOrUpdate();
		if (questionDB != null) {
			questionsRepository.delete(questionDB);
		}
		auditLogService.saveTransaction(questionDB.getAuditLog(), DELETE_ACTION, AUDIT_TXN_SUCCESS, TXN_DELETE_QUESTION);
	}

}
