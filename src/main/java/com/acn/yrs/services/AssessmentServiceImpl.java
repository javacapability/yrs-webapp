package com.acn.yrs.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.Assessment;
import com.acn.yrs.models.AssessmentStatus;
import com.acn.yrs.models.Asset;
import com.acn.yrs.models.BaseConstants;
import com.acn.yrs.models.Liability;
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
		AssessmentStatus assessmentStatus = new AssessmentStatus(ASSESSMENTPENDING);
		ArrayList<Assessment> activeAssessmentList = assessmentRepository.findAssessmentByAssessmentStatusAndClientInfoUserInfoUserIdIgnoreCaseAndClientInfoClientNameIgnoreCaseLike(assessmentStatus, userId, filter);
		assessmentStatus = new AssessmentStatus(ASSESSMENTRECOGIVEN);
		activeAssessmentList.addAll(assessmentRepository.findAssessmentByAssessmentStatusAndClientInfoUserInfoUserIdIgnoreCaseAndClientInfoClientNameIgnoreCaseLike(assessmentStatus, userId, filter));
		return activeAssessmentList;
	}

	@Override
	public ArrayList<Assessment> getArchivedAssessmentList(String userId,
			String filter) {
		AssessmentStatus assessmentStatus = new AssessmentStatus(ASSESSMENTARCHIVED);
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
		assessment.setArchivedDate(new Date());
		return assessment;
	}

	@Override
	public Assessment reactivateAssessment(int assessmentId) throws NoResultException {
		Assessment assessment = assessmentRepository.findOne(assessmentId);
		if(assessment==null) throw new NoResultException();
		assessment.setAssessmentStatus(assessmentStatusRepository.findOne(ASSESSMENTPENDING));
		assessment.setArchivedDate(null);
		assessment.setReactivationDate(new Date());
		return assessment;
	}

	@Override
	public Assessment saveAssessment(Assessment assessment) {

		getTotalAssetAndLiabilities(assessment);
		assessment.setAssessmentDate(new Date());
		assessment.setLastModificationDate(new Date());

		return assessmentRepository.save(assessment);
	}

	@Override
	public Assessment updateAssessment(Assessment assessment) {
		// TODO Auto-generated method stub
		getTotalAssetAndLiabilities(assessment);
		assessment.setLastModificationDate(new Date());

		return assessmentRepository.save(assessment);
	}

	private void getTotalAssetAndLiabilities(Assessment assessment) {
		BigDecimal totalAssets = new BigDecimal(0);

		List<Asset> assets = assessment.getAssets();
		if(assets!=null){
			for(Asset asset:assets){
				totalAssets = totalAssets.add(asset.getAssetamount());
			}
			assessment.setTotalAssets(totalAssets);
		}

		BigDecimal totalLiabilities = new BigDecimal(0);
		List<Liability> liabilities = assessment.getLiabilities();
		if(liabilities!=null){
			for(Liability liability:liabilities){
				totalLiabilities = totalLiabilities.add(liability.getLiabilityamount());
			}
			assessment.setTotalLiabilities(totalLiabilities);
		}
	}
}
