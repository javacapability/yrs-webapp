package com.acn.yrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.acn.yrs.models.BaseObject;
import com.acn.yrs.models.UserInfo;
import com.acn.yrs.repository.UserInfoRepository;
import com.google.gson.Gson;

@RestController
public class BaseController {

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

	/*public static HttpServletResponse getResponse(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setStatus();
        return response;
    }*/
	public ResponseEntity<Object> getResponse(Object object, HttpStatus status){

		return new ResponseEntity<Object>(new Gson().toJson(object), new HttpHeaders(), status);

	}

	public ResponseEntity<Object> getResponse(Object object, String tokenId, HttpStatus status){
		if(tokenId==null) return getResponse(object, status);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("tokenId", tokenId);
		httpHeaders.set("Access-Control-Allow-Origin", "*");
		httpHeaders.set("Access-Control-Allow-Methods", "POST, GET");
		httpHeaders.set("Access-Control-Allow-Headers", "x-requested-with");
		return new ResponseEntity<Object>(new Gson().toJson(object), httpHeaders , status);

	}

	public ResponseEntity<Object> checkUser(String userId, String tokenId){

		UserInfo userInfo = userInfoRepository.findUserInfoByUserId(userId.toUpperCase());

		if(userInfo==null){
			return getResponse(new BaseObject(HttpStatus.FORBIDDEN.toString(), "Invalid session"), HttpStatus.FORBIDDEN);
		}
		if(userInfo.getTokenId()==null){
			return getResponse(new BaseObject(HttpStatus.FORBIDDEN.toString(), "Session has expired"), HttpStatus.FORBIDDEN);
		}
		if(!userInfo.getTokenId().equalsIgnoreCase(tokenId)){
			return getResponse(new BaseObject(HttpStatus.FORBIDDEN.toString(), "Authentication failed"), HttpStatus.FORBIDDEN);
		}
		//return null if user is valid, session is valid, token is valid
		return null;
	}
}
