package com.acn.yrs.services;

import java.util.ArrayList;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.Assessment;
import com.acn.yrs.models.AssessmentStatus;
import com.acn.yrs.models.BaseConstants;
import com.acn.yrs.repository.AssessmentRepository;
import com.acn.yrs.repository.AssessmentStatusRepository;

@Service("assessmentService")
@Transactional
public class AssessmentServiceImpl extends BaseConstants implements AssessmentService {

	@Autowired
	AssessmentRepository assessmentRepository;
	@Autowired
	AssessmentStatusRepository assessmentStatusRepository;

	@Override
	public ArrayList<Assessment> getActiveAssessmentList(String userId, String filter) {
		AssessmentStatus assessmentStatus = new AssessmentStatus();
		assessmentStatus.setId(ASSESSMENTPENDING);
		ArrayList<Assessment> activeAssessmentList = assessmentRepository.findAssessmentByAssessmentStatusAndClientInfoUserInfoUserIdIgnoreCaseAndClientInfoClientNameIgnoreCaseLike(assessmentStatus, userId, filter);
		assessmentStatus = new AssessmentStatus();
		assessmentStatus.setId(ASSESSMENTRECOGIVEN);
		activeAssessmentList.addAll(assessmentRepository.findAssessmentByAssessmentStatusAndClientInfoUserInfoUserIdIgnoreCaseAndClientInfoClientNameIgnoreCaseLike(assessmentStatus, userId, filter));
		return activeAssessmentList;
	}

	@Override
	public ArrayList<Assessment> getArchivedAssessmentList(String userId,
			String filter) {
		AssessmentStatus assessmentStatus = new AssessmentStatus();
		assessmentStatus.setId(ASSESSMENTARCHIVED);
		ArrayList<Assessment> activeAssessmentList = assessmentRepository.findAssessmentByAssessmentStatusAndClientInfoUserInfoUserIdIgnoreCaseAndClientInfoClientNameIgnoreCaseLike(assessmentStatus, userId, filter);
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

	@Override
	public Assessment archiveAssessment(int assessmentId) throws NoResultException {
		Assessment assessment = assessmentRepository.findOne(assessmentId);
		if(assessment==null) throw new NoResultException();
		assessment.setAssessmentStatus(assessmentStatusRepository.findOne(ASSESSMENTARCHIVED));
		return assessment;
	}

	@Override
	public Assessment reactivateAssessment(int assessmentId) throws NoResultException {
		Assessment assessment = assessmentRepository.findOne(assessmentId);
		if(assessment==null) throw new NoResultException();
		assessment.setAssessmentStatus(assessmentStatusRepository.findOne(ASSESSMENTPENDING));
		return assessment;
	}
}
