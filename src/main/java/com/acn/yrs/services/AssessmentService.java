package com.acn.yrs.services;

import java.util.ArrayList;

import com.acn.yrs.models.Assessment;


public interface AssessmentService {

	ArrayList<Assessment> getActiveAssessmentList(String userId, String filter);
	ArrayList<Assessment> getArchivedAssessmentList(String userId, String filter);
	Assessment getAssessment(int assessmentId);
	Assessment getAssessmentFilterByAdvisorUserId(int assessmentId, String advisorUserId);
}
