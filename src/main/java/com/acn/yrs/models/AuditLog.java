package com.acn.yrs.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AUDITLOG")
public class AuditLog {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "datetime", nullable = false)
	private Date dateTime;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserInfo userInfo;

	@Column(name = "app")
	private Integer app;

	@Column(name = "status")
	private Integer status;

	@Column(name = "action")
	private Integer action;

	@Column(name = "errorcd")
	private String errorCd;

	@Column(name = "reason")
	private String reason;

	@Column(name = "oldtxndtls")
	private String oldTxnDtls;

	@Column(name = "newtxndtls")
	private String newTxnDtls;

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
	 * @return the dateTime
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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
	 * @return the app
	 */
	public Integer getApp() {
		return app;
	}

	/**
	 * @param app the app to set
	 */
	public void setApp(Integer app) {
		this.app = app;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the action
	 */
	public Integer getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(Integer action) {
		this.action = action;
	}

	/**
	 * @return the errorCd
	 */
	public String getErrorCd() {
		return errorCd;
	}

	/**
	 * @param errorCd the errorCd to set
	 */
	public void setErrorCd(String errorCd) {
		this.errorCd = errorCd;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the oldTxnDtls
	 */
	public String getOldTxnDtls() {
		return oldTxnDtls;
	}

	/**
	 * @param oldTxnDtls the oldTxnDtls to set
	 */
	public void setOldTxnDtls(String oldTxnDtls) {
		if(oldTxnDtls == "" || oldTxnDtls == null ){
			this.oldTxnDtls = "";
		} else {
			this.oldTxnDtls = oldTxnDtls;
		}
		
	}


	/**
	 * @return the newTxnDtls
	 */
	public String getNewTxnDtls() {
		return newTxnDtls;
	}

	/**
	 * @param newTxnDtls the newTxnDtls to set
	 */
	public void setNewTxnDtls(String newTxnDtls) {
		if(newTxnDtls == "" || newTxnDtls == null ){
			this.newTxnDtls = "";
		} else {
			this.newTxnDtls = newTxnDtls;
		}
		
	}

}
