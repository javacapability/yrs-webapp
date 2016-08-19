package com.acn.yrs.services;

import java.util.List;

import com.acn.yrs.models.Portfolio;
import com.acn.yrs.models.PortfolioWrapper;

public interface PortfolioService {

	public PortfolioWrapper saveAssessmentPortfolio(PortfolioWrapper portfolioWrapper);
	public Portfolio deleteAssessmentPortfolio(Integer portfolioId);
	public List<Portfolio> findPortfolioByAssessmentId(int assessmentId);
}
