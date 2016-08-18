package com.acn.yrs.models;

import java.util.List;

public class PortfolioWrapper extends ResponseObject {

	private List<Portfolio> investmentGoals;

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
