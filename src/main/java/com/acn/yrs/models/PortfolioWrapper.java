package com.acn.yrs.models;

import java.util.List;

public class PortfolioWrapper extends ResponseObject {

	private int assessmentId;
	private List<Portfolio> investmentGoals;


	/**
	 * @return the assessmentId
	 */
	public int getAssessmentId() {
		return assessmentId;
	}

	/**
	 * @param assessmentId the assessmentId to set
	 */
	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}



	/**
	 * @return the investmentGoals
	 */
	public List<Portfolio> getInvestmentGoals() {
		return investmentGoals;
	}

	/**
	 * @param investmentGoals the investmentGoals to set
	 */
	public void setInvestmentGoals(List<Portfolio> investmentGoals) {
		this.investmentGoals = investmentGoals;
	}
}
