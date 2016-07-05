package com.acn.yrs.services;

import java.util.List;

import com.acn.yrs.models.Assessment;


public interface AssessmentService {

	List<Assessment> getActiveAssessmentList(String userId, String filter);
	List<Assessment> getArchivedAssessmentList(String userId, String filter);

}
