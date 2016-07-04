package com.acn.yrs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acn.yrs.models.ErrorObject;
import com.acn.yrs.models.UserInfo;
import com.acn.yrs.repository.UserInfoRepository;

@Service("helper")
public class Helper {

	@Autowired
	UserInfoRepository userInfoRepository;

	public static String PASSWORD="HindiLahatNgPogiNagaartistaYungIbaJavaDeveloper";
	public static String ALGO="PBEWITHSHA256AND128BITAES-CBC-BC";
	//public static String ALGO="PBEWithMD5AndTripleDES";

	Logger LOG = LoggerFactory.getLogger(Helper.class);


	public Object packResponse(Object object, HttpStatus status){

		return new ResponseEntity<Object>(object, new HttpHeaders(), status);

	}

	public Object packResponseWithTokenId(Object object, String tokenId, HttpStatus status){
		if(tokenId==null) return packResponse(object, status);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("tokenId", tokenId);

		return new ResponseEntity<Object>(object, httpHeaders , status);

	}

	public Object checkUser(String userId, String tokenId){

		UserInfo userInfo = userInfoRepository.findUserInfoByUserId(userId.toUpperCase());

		if(userInfo==null){
			return packResponse(new ErrorObject(HttpStatus.FORBIDDEN.toString(), "Invalid session"), HttpStatus.FORBIDDEN);
		}
		if(userInfo.getTokenId()==null){
			return packResponse(new ErrorObject(HttpStatus.FORBIDDEN.toString(), "Session has expired"), HttpStatus.FORBIDDEN);
		}
		if(!userInfo.getTokenId().equalsIgnoreCase(tokenId)){
			return packResponse(new ErrorObject(HttpStatus.FORBIDDEN.toString(), "Authentication failed"), HttpStatus.FORBIDDEN);
		}
		//return null if user is valid, session is valid, token is valid
		return null;
	}
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


}
