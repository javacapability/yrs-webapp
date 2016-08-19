package com.acn.yrs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.Portfolio;
import com.acn.yrs.models.PortfolioWrapper;
import com.acn.yrs.repository.PortfoliosRepository;
import com.acn.yrs.utils.BaseConstants;

@Service("portfolioService")
@Transactional
public class PortfolioServiceImpl extends BaseConstants implements PortfolioService {

	@Autowired
	PortfoliosRepository portfolioRepository;

	/**
	 * @return the portfolioRepository
	 */
	public PortfoliosRepository getPortfolioRepository() {
		return portfolioRepository;
	}

	/**
	 * @param portfolioRepository the portfolioRepository to set
	 */
	public void setPortfolioRepository(PortfoliosRepository portfolioRepository) {
		this.portfolioRepository = portfolioRepository;
	}

	@Override
	public PortfolioWrapper saveAssessmentPortfolio(PortfolioWrapper portfolioWrapper) {
		// TODO Auto-generated method stub
		if(portfolioWrapper!=null){
			List<Portfolio> portfolioList = portfolioWrapper.getInvestmentGoals();
			portfolioRepository.save(portfolioList);
		}else{
			portfolioWrapper = new PortfolioWrapper();
			portfolioWrapper.setErrorCd(HASERROR);
			portfolioWrapper.setErrorMsg(ERR_PORTFOLIO_EMPTY);
		}
		return portfolioWrapper;
	}

	@Override
	public Portfolio deleteAssessmentPortfolio(Integer portfolioId) {

		Portfolio portfolio = portfolioRepository.findOne(portfolioId);
		if(portfolio!=null){
			portfolioRepository.delete(portfolioId);
		}
		return portfolio;
	}

	@Override
	public List<Portfolio> findPortfolioByAssessmentId(int assessmentId) {
		// TODO Auto-generated method stub
		return portfolioRepository.findPortfolioByAssessmentId(assessmentId);
	}

}
