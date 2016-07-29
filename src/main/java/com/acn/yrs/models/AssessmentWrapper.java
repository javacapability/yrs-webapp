package com.acn.yrs.models;

import java.io.File;
import java.util.List;

public class AssessmentWrapper extends ResponseObject {

	private int id;;
	private String accountNumber;
	private String clientName;
	private String birthday;
	private String jobTitleFieldWork;
	private String eduAttainment;
	private String signature;
	private String photo;
	private List<Questionnaire> survey;
	private List<Asset> assets;
	private List<Liability> liabilities;

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
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}
	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the jobTitleFieldWork
	 */
	public String getJobTitleFieldWork() {
		return jobTitleFieldWork;
	}
	/**
	 * @param jobTitleFieldWork the jobTitleFieldWork to set
	 */
	public void setJobTitleFieldWork(String jobTitleFieldWork) {
		this.jobTitleFieldWork = jobTitleFieldWork;
	}
	/**
	 * @return the eduAttainment
	 */
	public String getEduAttainment() {
		return eduAttainment;
	}
	/**
	 * @param eduAttainment the eduAttainment to set
	 */
	public void setEduAttainment(String eduAttainment) {
		this.eduAttainment = eduAttainment;
	}
	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}
	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
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
