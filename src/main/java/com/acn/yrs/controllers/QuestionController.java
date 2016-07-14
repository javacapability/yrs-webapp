package com.acn.yrs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acn.yrs.models.Question;
import com.acn.yrs.services.QuestionService;

@RestController
public class QuestionController extends BaseController{

	@Autowired
	QuestionService questionService;


	/*@RequestMapping(value = "/questions", method = RequestMethod.GET)
	 public HttpEntity<PagedResources<Question>> persons(final Pageable pageable,
	    final PagedResourcesAssembler<Question> assembler) {

	    Page<Question> questions = questionsRepository.findAllByIsActive(1, pageable);
	    return new ResponseEntity<>(assembler.toResource(questions), HttpStatus.OK);
	  }*/

	@RequestMapping(value="/getQuestionList", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getQuestionList(@RequestHeader String userId, @RequestHeader String tokenId) {
		try {
			ResponseEntity<Object> obj = checkUser(userId, tokenId);
			if(obj!=null){
				return obj;
			}
			List<Question> questionList = questionService.getQuestionList();

			return getResponse(questionList, tokenId, HttpStatus.OK);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getResponse("Error", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}


	/**
	 * @return the questionService
	 */
	public QuestionService getQuestionService() {
		return questionService;
	}


	/**
	 * @param questionService the questionService to set
	 */
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

}
