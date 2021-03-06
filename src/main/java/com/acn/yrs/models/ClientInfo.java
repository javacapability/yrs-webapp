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

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "CLIENTINFO")
public class ClientInfo extends ResponseObject{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	@Expose
	private Integer id;

	@Expose
	@Column(name = "clientname", nullable = false)
	private String clientName;


	@Column(name = "birthday", nullable = false)
	@Expose
	private Date birthday;

	@Column(name = "jobtitlefieldwork", nullable = false)
	@Expose
	private String jobTitleFieldWork;

	@Column(name = "eduattainment", nullable = false)
	@Expose
	private String eduAttainment;

	@Column(name = "financialpersonality")
	@Expose
	private String financialPersonality;

	@ManyToOne
	@JoinColumn(name = "advisorid")
	@Expose
	private UserInfo userInfo;

	@Column(name = "photo")
	@Expose
	private String photo;

	@Column(name = "signature")
	@Expose
	private String signature;

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
}
