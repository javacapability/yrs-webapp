package com.acn.yrs.models;

import org.springframework.http.HttpStatus;

public class BaseObject {

	private String errorCd;
	private String errorMsg;
	private HttpStatus httpStatus;

	public BaseObject(){

	}

	public BaseObject(String errorCd, String errorMsg){
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
}
