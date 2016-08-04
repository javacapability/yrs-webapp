package com.acn.yrs.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "PORTFOLIO")
public class Portfolio extends ResponseObject{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	@Expose
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "assessmentid")
	private Assessment assessment;

	@Column(name = "portfolioname", nullable = false)
	@Expose
	private String portfolioName;

	@Column(name = "currency", nullable = false)
	@Expose
	private String currency;

	@Column(name = "portfolioamount", nullable = false)
	@Expose
	private BigDecimal portfolioAmount;

	@Column(name = "investmenthorizon", nullable = false)
	@Expose
	private Integer investmentHorizon;

	@Column(name = "selectedrisktolerance", nullable = false)
	@Expose
	private String selectedRiskTolerance;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the assessment
	 */
	public Assessment getAssessment() {
		return assessment;
	}

	/**
	 * @param assessment the assessment to set
	 */
	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	/**
	 * @return the portfolioName
	 */
	public String getPortfolioName() {
		return portfolioName;
	}

	/**
	 * @param portfolioName the portfolioName to set
	 */
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the portfolioAmount
	 */
	public BigDecimal getPortfolioAmount() {
		return portfolioAmount;
	}

	/**
	 * @param portfolioAmount the portfolioAmount to set
	 */
	public void setPortfolioAmount(BigDecimal portfolioAmount) {
		this.portfolioAmount = portfolioAmount;
	}

	/**
	 * @return the investmentHorizon
	 */
	public Integer getInvestmentHorizon() {
		return investmentHorizon;
	}

	/**
	 * @param investmentHorizon the investmentHorizon to set
	 */
	public void setInvestmentHorizon(Integer investmentHorizon) {
		this.investmentHorizon = investmentHorizon;
	}

	/**
	 * @return the selectedRiskTolerance
	 */
	public String getSelectedRiskTolerance() {
		return selectedRiskTolerance;
	}

	/**
	 * @param selectedRiskTolerance the selectedRiskTolerance to set
	 */
	public void setSelectedRiskTolerance(String selectedRiskTolerance) {
		this.selectedRiskTolerance = selectedRiskTolerance;
	}

}
