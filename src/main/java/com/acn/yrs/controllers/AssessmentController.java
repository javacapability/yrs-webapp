package com.acn.yrs.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

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

import com.acn.yrs.models.Assessment;
import com.acn.yrs.models.Questionnaire;
import com.acn.yrs.models.ResponseObject;
import com.acn.yrs.models.SearchObject;
import com.acn.yrs.services.AssessmentService;
import com.acn.yrs.services.QuestionnaireService;

@RestController
public class AssessmentController extends BaseController{

	Logger LOG = LoggerFactory.getLogger(AssessmentController.class);

	@Autowired
	AssessmentService assessmentService;

	@Autowired
	QuestionnaireService questionnaireService;

	@RequestMapping(value="/getAssessmentList", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> getAssessmentList(@RequestHeader String userId, @RequestHeader String tokenId, @RequestBody SearchObject searchObject) {
		try {
			ResponseEntity<Object> obj = checkUser(userId, tokenId);
			if(obj!=null){
				return obj;
			}
			LOG.info("userId: " + userId);
			LOG.info("tokenId: " + tokenId);
			LOG.info("listType: " + searchObject.getListType());
			LOG.info("clientSearch: " + searchObject.getFilter());
			ArrayList<Assessment> assessmentList = null;
			String filter = "%" + searchObject.getFilter() + "%";
			if("active".equalsIgnoreCase(searchObject.getListType())){
				assessmentList = assessmentService.getActiveAssessmentList(userId, filter);
			}else{
				assessmentList = assessmentService.getArchivedAssessmentList(userId, filter);
			}
			return getResponse(assessmentList, tokenId, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getResponse("Error", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@RequestMapping(value="/getAssessment", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> getAssessment(@RequestHeader String userId, @RequestHeader String tokenId, @RequestBody SearchObject searchObject) {
		try {
			ResponseEntity<Object> obj = checkUser(userId, tokenId);
			if(obj!=null){
				return obj;
			}
			int assessmentId = searchObject.getId();
			LOG.info("assessmentId: " + assessmentId);
			Assessment assessment = assessmentService.getAssessmentFilterByAdvisorUserId(assessmentId, userId);
			if(assessment!=null){
				List<Questionnaire> survey = questionnaireService.findQuestionnaireByAssessmentId(assessmentId);
				assessment.setSurvey(survey);
			}

			return getResponse(assessment, tokenId, HttpStatus.OK);

		}catch(NoResultException e){
			//e.printStackTrace();
			return getResponse("Assessment Not Found",tokenId, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getResponse("Error", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	@RequestMapping(value="/archiveAssessment", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> archiveAssessment(@RequestHeader String userId, @RequestHeader String tokenId, @RequestBody SearchObject searchObject) {
		try {
			ResponseEntity<Object> obj = checkUser(userId, tokenId);
			if(obj!=null){
				return obj;
			}
			int assessmentId = searchObject.getId();
			LOG.info("assessmentId: " + assessmentId);
			assessmentService.archiveAssessment(assessmentId);

			return getResponse(new ResponseObject(), tokenId, HttpStatus.OK);
		}catch(NoResultException e){
			//e.printStackTrace();
			return getResponse("Assessment Not Found",tokenId, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getResponse("Error", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@RequestMapping(value="/reactivateAssessment", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> reactivateAssessment(@RequestHeader String userId, @RequestHeader String tokenId, @RequestBody SearchObject searchObject) {
		try {
			ResponseEntity<Object> obj = checkUser(userId, tokenId);
			if(obj!=null){
				return obj;
			}
			int assessmentId = searchObject.getId();
			LOG.info("assessmentId: " + assessmentId);
			assessmentService.reactivateAssessment(assessmentId);

			return getResponse(new ResponseObject(), tokenId, HttpStatus.OK);
		}catch(NoResultException e){
			//e.printStackTrace();
			return getResponse("Assessment Not Found",tokenId, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getResponse("Error", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
