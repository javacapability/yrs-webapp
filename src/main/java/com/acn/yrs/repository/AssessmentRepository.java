package com.acn.yrs.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.Assessment;
import com.acn.yrs.models.AssessmentStatus;

@RepositoryRestResource(path="assessments", exported=false)
public interface AssessmentRepository extends PagingAndSortingRepository<Assessment, Integer>{

	List<Assessment> findAssessmentByAssessmentStatus(AssessmentStatus assessmentStatus);
	List<Assessment> findAssessmentByAssessmentStatusAndClientInfoUserInfoUserIdIgnoreCaseAndClientInfoClientNameIgnoreCaseLike(
			AssessmentStatus assessmentStatus, String userId, String searchFilter);
	Assessment findAssessmentByIdAndClientInfoUserInfoUserIdIgnoreCase(int id, String advisorUserId);
}
