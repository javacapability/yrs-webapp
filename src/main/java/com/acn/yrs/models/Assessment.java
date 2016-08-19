package com.acn.yrs.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="ASSESSMENT")
public class Assessment extends ResponseObject{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	@Expose
	private int id;

	@OneToOne
	@JoinColumn(name = "clientid")
	@Expose
	private ClientInfo clientInfo;

	@Column(name = "accountnumber")
	@Expose
	private String accountNumber;

	@ManyToOne
	@JoinColumn(name = "assessmentstatus")
	@Expose
	private AssessmentStatus assessmentStatus;

	@Column(name = "totalassets")
	@Expose
	private BigDecimal totalAssets;

	@Column(name = "totalliabilities")
	@Expose
	private BigDecimal totalLiabilities;

	@Column(name = "losstolerance")
	@Expose
	private BigDecimal lossTolerance;

	@Column(name = "financialcondition")
	@Expose
	private String financialCondition;

	@Column(name = "assessmentdate")
	@Expose
	private Date assessmentDate;

	@Column(name = "lastmodificationdate")
	@Expose
	private Date lastModificationDate;

	@Column(name = "archiveddate")
	@Expose
	private Date archivedDate;

	@Column(name = "reactivationdate")
	@Expose
	private Date reactivationDate;

	//modified this part
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "assessmentid")
	@Expose
	private List<Asset> assets;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "assessmentid")
	@Expose
	private List<Liability> liabilities;

	//@OneToMany(mappedBy = "assessment", cascade = CascadeType.ALL)
	//@Transient
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "assessmentid")
	@Expose
	private List<Questionnaire> survey;

	/*@Override
	public String toString() {

		   StringBuffer sb = new StringBuffer();
		    sb.append("Assessment = [ ");
		    sb.append(" priorityNumber: " +this.priorityNumber);
		    sb.append(" questionTxt: " +this.questionTxt);
		    sb.append(" yesWeight: " +this.yesWeight);
		    sb.append(" noWeight: " +this.noWeight);
		    sb.append(" trueWeight: " +this.trueWeight);
		    sb.append(" falseWeight: " +this.falseWeight);
		    sb.append("]");

		    return sb.toString();
	}*/

	public Assessment(int assessmentId) {
		this.id = assessmentId;
	}

	public Assessment() {

	}

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

	/**
	 * @return the archivedDate
	 */
	public Date getArchivedDate() {
		return archivedDate;
	}

	/**
	 * @param archivedDate the archivedDate to set
	 */
	public void setArchivedDate(Date archivedDate) {
		this.archivedDate = archivedDate;
	}

	/**
	 * @return the assets
	 */
	public List<Asset> getAssets() {
		return assets;
	}

	/**
	 * @param assets the assets to set
	 */
	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}

	/**
	 * @return the liabilities
	 */
	public List<Liability> getLiabilities() {
		return liabilities;
	}

	/**
	 * @param liabilities the liabilities to set
	 */
	public void setLiabilities(List<Liability> liabilities) {
		this.liabilities = liabilities;
	}
}
