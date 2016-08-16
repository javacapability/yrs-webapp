package com.acn.yrs.controllers;

import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.acn.yrs.models.ResponseObject;
import com.acn.yrs.models.UserInfo;
import com.acn.yrs.repository.UserInfoRepository;
import com.acn.yrs.services.UserService;
import com.acn.yrs.utils.BaseConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class BaseController extends BaseConstants {


	@Autowired
	UserService userService;
	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
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
				status = HttpStatus.NOT_FOUND;
				((ResponseObject) object).setHttpStatus(status);
				((ResponseObject) object).setErrorCd(HASERROR);
				((ResponseObject) object).setErrorMsg(ERR_NORECORDFOUND);
			}
		}
		if(object==null){
			object = new ResponseObject();
			status = HttpStatus.NOT_FOUND;
			((ResponseObject) object).setHttpStatus(status);
			((ResponseObject) object).setErrorCd(HASERROR);
			((ResponseObject) object).setErrorMsg(ERR_NORECORDFOUND);
		}

		//implemented a filter to handle http headers - CorsFilter.java
		//httpHeaders.set("Access-Control-Allow-Origin", "*");
		//httpHeaders.set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");//, PATCH, PUT, DELETE, OPTIONS");
		//httpHeaders.set("Access-Control-Allow-Headers", "Content-Type, Accept, Access-Control-Allow-Origin, userId, tokenId");
		//httpHeaders.set("Accept", "application/json");


		//this will hide fields without @Expose annotation from the JSON response
		//can hide unwanted data
		//can prevent cyclic dependencies
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		object = object!=null?gson.toJson(object):object;
		return new ResponseEntity<Object>(object, httpHeaders , status);
	}


	public ResponseEntity<Object> checkUser(String userId, String tokenId){

		UserInfo userInfo = userService.findUserInfoByUserId(userId.toUpperCase());

		if(userInfo==null){
			return getResponse(new UserInfo(HttpStatus.FORBIDDEN,HASERROR, ERR_INVALID_SESSION), HttpStatus.FORBIDDEN);
		}
		if(userInfo.getTokenId()==null){
			return getResponse(new UserInfo(HttpStatus.FORBIDDEN,HASERROR, ERR_SESSION_EXPIRED), HttpStatus.FORBIDDEN);
		}
		if(!userInfo.getTokenId().equalsIgnoreCase(tokenId)){
			return getResponse(new UserInfo(HttpStatus.FORBIDDEN,HASERROR, ERR_AUTH_FAILED), HttpStatus.FORBIDDEN);
		}
		//return null if user is valid, session is valid, token is valid
		return null;
	}


}
