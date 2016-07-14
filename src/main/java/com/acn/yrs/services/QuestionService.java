package com.acn.yrs.services;

import java.util.List;

import com.acn.yrs.models.Question;

public interface QuestionService {

	public List<Question> getQuestionList();
	public Question create(Question questionInfo);
	public Question update(Question questionInfo);
	public void delete(int questionId);
	
}
