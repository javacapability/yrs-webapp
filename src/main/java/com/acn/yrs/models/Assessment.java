package com.acn.yrs.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ASSESSMENT")
public class Assessment extends ResponseObject{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@OneToOne
	@JoinColumn(name = "clientid")
	private ClientInfo clientInfo;

	@Column(name = "accountnumber")
	private String accountNumber;

	@ManyToOne
	@JoinColumn(name = "assessmentstatus")
	private AssessmentStatus assessmentStatus;

	@Column(name = "totalassets")
	private BigDecimal totalAssets;

	@Column(name = "totalliabilities")
	private BigDecimal totalLiabilities;

	@Column(name = "losstolerance")
	private BigDecimal lossTolerance;

	@Column(name = "financialcondition")
	private String financialCondition;

	@Column(name = "assessmentdate")
	private Date assessmentDate;

	@Column(name = "lastmodificationdate")
	private Date lastModificationDate;

	@Column(name = "reactivationdate")
	private Date reactivationDate;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the clientInfo
	 */
	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	/**
	 * @param clientInfo the clientInfo to set
	 */
	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	/**
	 * @return the assessmentStatus
	 */
	public AssessmentStatus getAssessmentStatus() {
		return assessmentStatus;
	}

	/**
	 * @param assessmentStatus the assessmentStatus to set
	 */
	public void setAssessmentStatus(AssessmentStatus assessmentStatus) {
		this.assessmentStatus = assessmentStatus;
	}

	/**
	 * @return the totalAssets
	 */
	public BigDecimal getTotalAssets() {
		return totalAssets;
	}

	/**
	 * @param totalAssets the totalAssets to set
	 */
	public void setTotalAssets(BigDecimal totalAssets) {
		this.totalAssets = totalAssets;
	}

	/**
	 * @return the totalLiabilities
	 */
	public BigDecimal getTotalLiabilities() {
		return totalLiabilities;
	}

	/**
	 * @param totalLiabilities the totalLiabilities to set
	 */
	public void setTotalLiabilities(BigDecimal totalLiabilities) {
		this.totalLiabilities = totalLiabilities;
	}

	/**
	 * @return the lossTolerance
	 */
	public BigDecimal getLossTolerance() {
		return lossTolerance;
	}

	/**
	 * @param lossTolerance the lossTolerance to set
	 */
	public void setLossTolerance(BigDecimal lossTolerance) {
		this.lossTolerance = lossTolerance;
	}

	/**
	 * @return the financialCondition
	 */
	public String getFinancialCondition() {
		return financialCondition;
	}

	/**
	 * @param financialCondition the financialCondition to set
	 */
	public void setFinancialCondition(String financialCondition) {
		this.financialCondition = financialCondition;
	}

	/**
	 * @return the assessmentDate
	 */
	public Date getAssessmentDate() {
		return assessmentDate;
	}

	/**
	 * @param assessmentDate the assessmentDate to set
	 */
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	/**
	 * @return the lastModificationDate
	 */
	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	/**
	 * @param lastModificationDate the lastModificationDate to set
	 */
	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * @return the reactivationDate
	 */
	public Date getReactivationDate() {
		return reactivationDate;
	}

	/**
	 * @param reactivationDate the reactivationDate to set
	 */
	public void setReactivationDate(Date reactivationDate) {
		this.reactivationDate = reactivationDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@OneToMany(mappedBy = "assessment", cascade = CascadeType.ALL)
	private List<Questionnaire> survey;

	/**
	 * @return the survey
	 */
	public List<Questionnaire> getSurvey() {
		return survey;
	}

	/**
	 * @param survey the survey to set
	 */
	public void setSurvey(List<Questionnaire> survey) {
		this.survey = survey;
	}
}
