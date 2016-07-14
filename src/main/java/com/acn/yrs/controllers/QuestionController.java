package com.acn.yrs.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
    
	
	Logger LOG = LoggerFactory.getLogger(QuestionController.class);

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
    
	
	
	/**
     * This method is for admin user to create a question 
     * 
     * @param question
     * @param userId
     * @param tokenId
     * @return
     */
	@RequestMapping(value = "/question/create", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> create(@RequestBody Question question,
			@RequestHeader String userId, @RequestHeader String tokenId) {

		LOG.debug("Create New Question");
		ResponseEntity<Object> validity = checkUser(userId, tokenId);
		if (validity == null) {
			question = questionService.create(question);
			return getResponse(question,tokenId, HttpStatus.OK);
		} else {
			return validity;
		}

	}

	/**
	 * This method is for admin user to update a question details
	 * 
	 * @param questionInfo
	 * @param userId
	 * @param tokenId
	 * @return
	 */
	@RequestMapping(value = "/question/update", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> update(@RequestBody Question questionInfo,
			@RequestHeader String userId, @RequestHeader String tokenId) {

		LOG.debug("Update Question Details");
		ResponseEntity<Object> validity = checkUser(userId, tokenId);
		if (validity == null) {
			questionInfo = questionService.update(questionInfo);
			return getResponse(questionInfo, tokenId, HttpStatus.OK);
		} else {
			return validity;
		}

	}
    /**
     *This method is for admin user to delete a question  
     * 
     * @param questionInfo
     * @param userId
     * @param tokenId
     * @return
     */
	@RequestMapping(value = "/question/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> delete(@RequestBody Question questionInfo,
			@RequestHeader String userId, @RequestHeader String tokenId) {

		LOG.debug("Delete Question Details");
		ResponseEntity<Object> validity = checkUser(userId, tokenId);
		if (validity == null) {
			questionService.delete(questionInfo.getId());
			return getResponse("Question Deleted",tokenId, HttpStatus.OK);
		} else {
			return validity;
		}

	}
}
