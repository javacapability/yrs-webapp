package com.acn.yrs.services;

import java.util.List;

import com.acn.yrs.models.Assessment;


public interface AssessmentService {

	List<Assessment> getActiveAssessmentList(String userId, String filter);
	List<Assessment> getArchivedAssessmentList(String userId, String filter);
	Assessment getAssessment(int assessmentId);
	Assessment getAssessmentFilterByAdvisorUserId(int assessmentId, String advisorUserId);
}
