package com.acn.yrs.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.AuditLog;
import com.acn.yrs.models.Question;
import com.acn.yrs.repository.QuestionsRepository;
import com.acn.yrs.utils.BaseConstants;

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
		AuditLog auditLog = auditLogService.saveTransaction(questionInfo,
				SAVE_ACTION, AUDIT_TXN_SUCCESS, TXN_CREATE_QUESTION);
		try{
			Question questionDB = questionsRepository.findByQuestionTxt(questionInfo.getQuestionTxt());
			if (questionDB == null) {
				questionsRepository.save(questionInfo);
			}else{
				auditLog.setStatus(AUDIT_TXN_FAIL);
				auditLog.setReason(ERR_QUESTION_EXISTING);
				auditLog = auditLogService.updateTransaction(auditLog, questionDB);
			}

		}catch(Exception e){
			e.printStackTrace();
			auditLog = auditLogService.updateTransaction(auditLog, questionInfo, AUDIT_TXN_FAIL, e.getMessage());
		}
		return questionInfo;
	}

	@Override
	public Question update(Question questionInfo) {
		LOG.info("Update Question Service()");
		Question questionDB = questionsRepository.findOne(questionInfo.getId());

		AuditLog auditLog = auditLogService.saveTransaction(questionDB,
				SAVE_ACTION, AUDIT_TXN_SUCCESS, TXN_CREATE_QUESTION);

		try{
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
				questionsRepository.save(questionDB);

				auditLogService.updateTransaction(auditLog,questionDB);
			}else{
				auditLog = auditLogService.updateTransaction(auditLog, questionDB, AUDIT_TXN_FAIL, ERR_NO_QUESTIONS_FOUND);
			}
		}catch(Exception e){
			e.printStackTrace();
			auditLogService.updateTransaction(auditLog,questionDB, AUDIT_TXN_FAIL, e.getMessage());
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

		AuditLog auditLog = auditLogService.saveTransaction(questionDB, DELETE_ACTION, AUDIT_TXN_SUCCESS, TXN_DELETE_QUESTION);

		try {
			if (questionDB != null) {
				questionsRepository.delete(questionDB);
			}else{
				auditLog = auditLogService.updateTransaction(auditLog, questionDB, AUDIT_TXN_FAIL, ERR_NO_QUESTIONS_FOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			auditLog = auditLogService.updateTransaction(auditLog, questionDB, AUDIT_TXN_FAIL, e.getMessage());
		}

	}

}
