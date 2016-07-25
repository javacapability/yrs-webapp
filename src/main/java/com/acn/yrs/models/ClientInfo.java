package com.acn.yrs.models;

import java.io.File;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "CLIENTINFO")
public class ClientInfo extends ResponseObject{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "clientname", nullable = false)
	private String clientName;


	@Column(name = "birthday", nullable = false)
	private Date birthday;

	@Column(name = "jobtitlefieldwork", nullable = false)
	private String jobTitleFieldWork;

	@Column(name = "eduattainment", nullable = false)
	private String eduAttainment;

	@Column(name = "financialpersonality")
	private String financialPersonality;

	@ManyToOne
	@JoinColumn(name = "advisorid")
	private UserInfo userInfo;

	@Column(name = "photo")
	private File photo;

	@Column(name = "signature")
	private File signature;

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
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
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
	 * @return the financialPersonality
	 */
	public String getFinancialPersonality() {
		return financialPersonality;
	}

	/**
	 * @param financialPersonality the financialPersonality to set
	 */
	public void setFinancialPersonality(String financialPersonality) {
		this.financialPersonality = financialPersonality;
	}

	/**
	 * @return the userInfo
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * @return the photo
	 */
	public File getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(File photo) {
		this.photo = photo;
	}

	/**
	 * @return the signature
	 */
	public File getSignature() {
		return signature;
	}

	/**
	 * @param signature the signature to set
	 */
	public void setSignature(File signature) {
		this.signature = signature;
	}
}
