package com.acn.yrs.models;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.acn.yrs.utils.BaseConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class ResponseObject extends BaseConstants{

	//default values
	private String adminId=APP_DEFAULT_USER;
	private String appOrigin=APP_ORIGIN_MOBILE;

	@Expose
	private String errorCd;
	@Expose
	private String errorMsg;
	@Expose
	private String responseMsg;
	/**
	 * @return the responseMsg
	 */
	public String getResponseMsg() {
		return responseMsg;
	}


	/**
	 * @param responseMsg the responseMsg to set
	 */
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}


	protected HttpStatus httpStatus;

	/**
	 * @return the adminId
	 */
	public String getAdminId() {
		return adminId;
	}


	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}


	/**
	 * @return the appOrigin
	 */
	public String getAppOrigin() {
		return appOrigin;
	}


	/**
	 * @param appOrigin the appOrigin to set
	 */
	public void setAppOrigin(String appOrigin) {
		this.appOrigin = appOrigin;
	}


	protected List<Question> questions;
	protected Assessment assessment;

	public ResponseObject(){

	}


	public ResponseObject(HttpStatus httpStatus, String errorCd, String errorMsg) {
		setHttpStatus(httpStatus);
		setErrorCd(errorCd);
		setErrorMsg(errorMsg);

	}

	public ResponseObject(String errorCd, String errorMsg){
		setErrorCd(errorCd);
		setErrorMsg(errorMsg);
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
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}


	/**
	 * @return the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}


	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
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


	public String toString(){
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return this!=null?gson.toJson(this):"";
	}

/*
    //audit Logs
	protected AuditLog auditLog = new AuditLog();

	 public void preSaveOrUpdate(){
		   auditLog.setOldTxnDtls(this.toString());
	 }

	 public void postSaveOrUpdate(){
		 auditLog.setNewTxnDtls(this.toString());
	 }


	*//**
	 * @return the auditLog
	 *//*
	public AuditLog getAuditLog() {
		return auditLog;
	}


	*//**
	 * @param auditLog the auditLog to set
	 *//*
	public void setAuditLog(AuditLog auditLog) {
		this.auditLog = auditLog;
	}
*/


}
