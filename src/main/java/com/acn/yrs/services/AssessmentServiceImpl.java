package com.acn.yrs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.Assessment;
import com.acn.yrs.models.AssessmentStatus;
import com.acn.yrs.repository.AssessmentRepository;
import com.acn.yrs.repository.AssessmentStatusRepository;

@Service("assessmentService")
@Transactional
public class AssessmentServiceImpl implements AssessmentService {

	@Autowired
	AssessmentRepository assessmentRepository;
	@Autowired
	AssessmentStatusRepository assessmentStatusRepository;

	@Override
	public List<Assessment> getActiveAssessmentList(String userId, String filter) {
		AssessmentStatus assessmentStatus = assessmentStatusRepository.findOne(1);
		List<Assessment> activeAssessmentList = assessmentRepository.findAssessmentByAssessmentStatusAndClientInfoUserInfoUserIdIgnoreCaseAndClientInfoClientNameIgnoreCaseLike(assessmentStatus, userId, filter);
		assessmentStatus = assessmentStatusRepository.findOne(2);
		activeAssessmentList.addAll(assessmentRepository.findAssessmentByAssessmentStatusAndClientInfoUserInfoUserIdIgnoreCaseAndClientInfoClientNameIgnoreCaseLike(assessmentStatus, userId, filter));
		return activeAssessmentList;
	}

	@Override
	public List<Assessment> getArchivedAssessmentList(String userId,
			String filter) {
		AssessmentStatus assessmentStatus = new AssessmentStatus();
		assessmentStatus.setId(3);
		List<Assessment> activeAssessmentList = assessmentRepository.findAssessmentByAssessmentStatusAndClientInfoUserInfoUserIdIgnoreCaseAndClientInfoClientNameIgnoreCaseLike(assessmentStatus, userId, filter);
		return activeAssessmentList;
	}

	@Override
	public Assessment getAssessment(int assessmentId) {
		return assessmentRepository.findOne(assessmentId);
	}

	@Override
	public Assessment getAssessmentFilterByAdvisorUserId(int assessmentId, String advisorUserId) {
		return assessmentRepository.findAssessmentByIdAndClientInfoUserInfoUserIdIgnoreCase(assessmentId, advisorUserId);
	}
}
