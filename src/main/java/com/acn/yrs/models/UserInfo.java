package com.acn.yrs.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERINFO")
public class UserInfo{

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "userid", nullable = false)
	private String userId;

	@Column(name = "nm", nullable = false)
	private String nm;

	@Column(name = "usergroup", nullable = false)
	private int userGroup;

	@Column(name = "pswd")
	private String pswd;

	@Column(name = "tokenid")
	private String tokenId;

	@Column(name = "email")
	private String email;

	@Column(name = "updt")
	private Date upDt;

	@Column(name = "birthday")
	private Date birthday;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the nm
	 */
	public String getNm() {
		return nm;
	}

	/**
	 * @param nm
	 *            the nm to set
	 */
	public void setNm(String nm) {
		this.nm = nm;
	}

	/**
	 * @return the userGroup
	 */
	public int getUserGroup() {
		return userGroup;
	}

	/**
	 * @param userGroup
	 *            the userGroup to set
	 */
	public void setUserGroup(int userGroup) {
		this.userGroup = userGroup;
	}

	/**
	 * @return the password
	 */
	public String getPswd() {
		return pswd;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	/**
	 * @return the sessionId
	 */
	public String getTokenId() {
		return tokenId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the upDt
	 */
	public Date getUpDt() {
		return upDt;
	}

	/**
	 * @param upDt
	 *            the upDt to set
	 */
	public void setUpDt(Date upDt) {
		this.upDt = upDt;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", name=" + nm
				+ ", userGroup=" + userGroup + ", email="
				+ email + ",birthday=" + birthday + "]";
	}
}
