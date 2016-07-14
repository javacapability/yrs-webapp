package com.acn.yrs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.acn.yrs.models.BaseConstants;
import com.acn.yrs.models.ResponseObject;
import com.acn.yrs.models.UserInfo;
import com.acn.yrs.repository.UserInfoRepository;
import com.google.gson.Gson;

@RestController
public class BaseController extends BaseConstants {

	@Autowired
	UserInfoRepository userInfoRepository;

	/**
	 * @return the userInfoRepository
	 */
	public UserInfoRepository getUserInfoRepository() {
		return userInfoRepository;
	}

	/**
	 * @param userInfoRepository the userInfoRepository to set
	 */
	public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}



	public ResponseEntity<Object> getResponse(Object object, HttpStatus status){

		return setResponse(object, status, new HttpHeaders());
	}
	public ResponseEntity<Object> getResponse(Object object, String tokenId, HttpStatus status){
		if(tokenId==null) return getResponse(object, status);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("tokenId", tokenId);
		return setResponse(object, status, httpHeaders);
	}

	private ResponseEntity<Object> setResponse(Object object,
			HttpStatus status, HttpHeaders httpHeaders) {
		if(object instanceof ResponseObject){
			if(status.equals(HttpStatus.OK)){
				((ResponseObject) object).setErrorCd(HASNOERROR);
			}else{
				((ResponseObject) object).setErrorCd(HASERROR);
			}
			((ResponseObject) object).setHttpStatus(status);
		}else if(object instanceof List){
			if(((List) object).size()<=0){
				object = new ResponseObject();
				((ResponseObject) object).setErrorCd(HASERROR);
				((ResponseObject) object).setErrorMsg(NORECORDFOUND);
			}
		}
		if(object==null){
			object = new ResponseObject();
			((ResponseObject) object).setErrorCd(HASERROR);
			((ResponseObject) object).setErrorMsg(NORECORDFOUND);
		}
		httpHeaders.set("Access-Control-Allow-Origin", "*");
		httpHeaders.set("Access-Control-Allow-Methods", "POST, GET");
		httpHeaders.set("Access-Control-Allow-Headers", "x-requested-with");

		object = object!=null?new Gson().toJson(object):object;
		return new ResponseEntity<Object>(object, httpHeaders , status);
	}


	public ResponseEntity<Object> checkUser(String userId, String tokenId){

		UserInfo userInfo = userInfoRepository.findUserInfoByUserId(userId.toUpperCase());

		if(userInfo==null){
			return getResponse(new UserInfo(HttpStatus.FORBIDDEN,HASERROR, INVALID_SESSION), HttpStatus.FORBIDDEN);
		}
		if(userInfo.getTokenId()==null){
			return getResponse(new UserInfo(HttpStatus.FORBIDDEN,HASERROR, SESSION_EXPIRED), HttpStatus.FORBIDDEN);
		}
		if(!userInfo.getTokenId().equalsIgnoreCase(tokenId)){
			return getResponse(new UserInfo(HttpStatus.FORBIDDEN,HASERROR, AUTH_FAILED), HttpStatus.FORBIDDEN);
		}
		//return null if user is valid, session is valid, token is valid
		return null;
	}
}
