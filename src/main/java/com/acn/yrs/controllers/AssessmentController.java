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

import com.acn.yrs.models.Assessment;
import com.acn.yrs.models.SearchObject;
import com.acn.yrs.services.AssessmentService;
import com.google.gson.Gson;

@RestController
public class AssessmentController extends BaseController{

	Logger LOG = LoggerFactory.getLogger(AssessmentController.class);

	@Autowired
	AssessmentService assessmentService;

	@RequestMapping(value="/getAssessmentList", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> getAssessmentList(@RequestHeader String userId, @RequestHeader String tokenId, @RequestBody SearchObject searchObject) {

		//checkUser(userId, tokenId);
		try {
			LOG.info("userId: " + userId);
			LOG.info("tokenId: " + tokenId);
			LOG.info("listType: " + searchObject.getListType());
			LOG.info("clientSearch: " + searchObject.getFilter());
			List<Assessment> assessmentList = null;

			if("active".equalsIgnoreCase(searchObject.getListType())){
				assessmentList = assessmentService.getActiveAssessmentList(userId, searchObject.getFilter());
			}else{
				assessmentList = assessmentService.getArchivedAssessmentList(userId, searchObject.getFilter());
			}

			return getResponse(assessmentList, tokenId, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getResponse("Error", tokenId, HttpStatus.SERVICE_UNAVAILABLE);
		}


	}
}
