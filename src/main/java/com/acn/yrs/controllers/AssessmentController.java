package com.acn.yrs.controllers;

import java.util.ArrayList;
import java.util.Date;
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
import com.acn.yrs.models.AssessmentStatus;
import com.acn.yrs.models.AssessmentWrapper;
import com.acn.yrs.models.ClientInfo;
import com.acn.yrs.models.Question;
import com.acn.yrs.models.Questionnaire;
import com.acn.yrs.models.ResponseObject;
import com.acn.yrs.models.SearchObject;
import com.acn.yrs.services.AssessmentService;
import com.acn.yrs.services.ClientInfoService;
import com.acn.yrs.services.QuestionService;
import com.acn.yrs.services.QuestionnaireService;
import com.acn.yrs.services.UserService;

@RestController
public class AssessmentController extends BaseController{

	Logger LOG = LoggerFactory.getLogger(AssessmentController.class);

	@Autowired
	AssessmentService assessmentService;
	@Autowired
	QuestionnaireService questionnaireService;
	@Autowired
	QuestionService questionService;
	@Autowired
	ClientInfoService clientInfoService;
	@Autowired
	UserService userService;

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
			return getResponse(ERR_ASSESSMENTNOTFOUND,tokenId, HttpStatus.NOT_FOUND);
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
			return getResponse(ERR_ASSESSMENTNOTFOUND,tokenId, HttpStatus.NOT_FOUND);

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
			//if(assessment==null) return getResponse(ASSESSMENTNOTFOUND,HttpStatus.NOT_ACCEPTABLE);
			return getResponse(ERR_ASSESSMENTNOTFOUND,tokenId, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getResponse("Error", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@RequestMapping(value="/createNewAssessment", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> createNewAssessment(@RequestHeader String userId, @RequestHeader String tokenId) {
		try{
			ResponseEntity<Object> obj = checkUser(userId, tokenId);
			if(obj!=null){
				return obj;
			}
			ResponseObject response = new ResponseObject();

			List<Question> questionList = questionService.getQuestionList();
			if(questionList.size()>0)
			{
				response.setErrorCd(HASNOERROR);
				response.setQuestions(questionList);
			}else{
				throw new NoResultException();
			}

			return getResponse(response,tokenId, HttpStatus.OK);
		}catch(NoResultException e){
			//e.printStackTrace();
			return getResponse(ERR_NO_QUESTIONS_FOUND,tokenId, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getResponse("Error", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@RequestMapping(value="/saveNewAssessment", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> saveNewAssessment(@RequestHeader String userId, @RequestHeader String tokenId, @RequestBody AssessmentWrapper assessmentWrapper) {
		try{
			ResponseEntity<Object> obj = checkUser(userId, tokenId);
			if(obj!=null){
				return obj;
			}
			ResponseObject response = new ResponseObject();
			Assessment assessment = new Assessment();
			assessment.setAccountNumber(assessmentWrapper.getAccountNumber());

			ClientInfo clientInfo = new ClientInfo();
			clientInfo.setClientName(assessmentWrapper.getClientName());
			clientInfo.setBirthday(new Date(assessmentWrapper.getBirthday()));
			clientInfo.setJobTitleFieldWork(assessmentWrapper.getJobTitleFieldWork());
			clientInfo.setEduAttainment(assessmentWrapper.getEduAttainment());
			clientInfo.setUserInfo(userService.findUserInfoByUserId(userId));
			clientInfo.setSignature(assessmentWrapper.getSignature());
			clientInfo.setPhoto(assessmentWrapper.getPhoto());

			clientInfo = clientInfoService.checkAndSaveClientInfo(clientInfo);
			if(clientInfo.getErrorCd().equals(HASERROR)){
				return getResponse(clientInfo,tokenId,HttpStatus.NOT_ACCEPTABLE);
			}

			assessment.setClientInfo(clientInfo);
			assessment.setAssets(assessmentWrapper.getAssets());
			assessment.setLiabilities(assessmentWrapper.getLiabilities());
			assessment.setSurvey(assessmentWrapper.getSurvey());
			assessment.setAssessmentStatus(new AssessmentStatus(ASSESSMENTPENDING));

			response = validateAssessment(assessment);
			if(response.getErrorCd().equals(HASERROR)){
				return getResponse(response	,tokenId, HttpStatus.NOT_ACCEPTABLE);
			}

			return getResponse(response,tokenId, HttpStatus.OK);
		}catch(NoResultException e){
			e.printStackTrace();
			return getResponse(ERR_NO_QUESTIONS_FOUND,tokenId, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getResponse("Error", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@RequestMapping(value="/updateAssessment", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> updateAssessment(@RequestHeader String userId, @RequestHeader String tokenId, @RequestBody AssessmentWrapper assessmentWrapper) {
		try{
			ResponseEntity<Object> obj = checkUser(userId, tokenId);
			if(obj!=null){
				return obj;
			}
			ResponseObject response = new ResponseObject();
			Assessment assessment = assessmentService.getAssessment(assessmentWrapper.getId());
			if(assessment==null){
				response.setErrorCd(HASERROR);;
				response.setErrorMsg(ERR_ASSESSMENTNOTFOUND);
				return getResponse(response,tokenId,HttpStatus.NOT_ACCEPTABLE);
			}
			assessment.setAccountNumber(assessmentWrapper.getAccountNumber()==null?assessment.getAccountNumber():assessmentWrapper.getAccountNumber());


			ClientInfo clientInfo = assessment.getClientInfo()==null?new ClientInfo():assessment.getClientInfo();
			clientInfo.setClientName(assessmentWrapper.getClientName()==null?clientInfo.getClientName():assessmentWrapper.getClientName());
			clientInfo.setBirthday(assessmentWrapper.getBirthday()==null?clientInfo.getBirthday():new Date(assessmentWrapper.getBirthday()));
			clientInfo.setJobTitleFieldWork(assessmentWrapper.getJobTitleFieldWork()==null?clientInfo.getJobTitleFieldWork():assessmentWrapper.getJobTitleFieldWork());
			clientInfo.setEduAttainment(assessmentWrapper.getEduAttainment()==null?clientInfo.getEduAttainment():assessmentWrapper.getEduAttainment());
			clientInfo.setUserInfo(userService.findUserInfoByUserId(userId));
			clientInfo.setSignature(assessmentWrapper.getSignature()==null?clientInfo.getSignature():assessmentWrapper.getSignature());
			clientInfo.setPhoto(assessmentWrapper.getPhoto()==null?clientInfo.getPhoto():assessmentWrapper.getPhoto());

			clientInfo = clientInfoService.saveClientInfo(clientInfo);

			if(clientInfo.getErrorCd().equals(HASERROR)){
				return getResponse(clientInfo,tokenId,HttpStatus.NOT_ACCEPTABLE);
			}

			assessment.setClientInfo(clientInfo);
			assessment.setAssets(assessmentWrapper.getAssets());
			assessment.setLiabilities(assessmentWrapper.getLiabilities());
			assessment.setSurvey(assessmentWrapper.getSurvey());
			assessment.setAssessmentStatus(new AssessmentStatus(ASSESSMENTPENDING));

			response = validateAssessment(assessment);
			if(response.getErrorCd().equals(HASERROR)){
				return getResponse(response,tokenId,HttpStatus.NOT_ACCEPTABLE);
			}

			return getResponse(response,tokenId, HttpStatus.OK);
		}catch(NoResultException e){
			e.printStackTrace();
			return getResponse(ERR_NO_QUESTIONS_FOUND,tokenId, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getResponse("Error", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	private Assessment validateAssessment(Assessment assessment) {
		if(assessment==null){
			assessment = new Assessment();
			assessment.setErrorCd(HASERROR);
			assessment.setErrorMsg(ERR_ASSESSMENT_ERROR_PAYLOAD_NULL);
		}else{
			assessment.setErrorCd(HASNOERROR);
			if(assessment.getId()==0){
				assessment = assessmentService.saveAssessment(assessment);
			}else{
				assessment = assessmentService.updateAssessment(assessment);
			}
		}
		return assessment;
	}
}
