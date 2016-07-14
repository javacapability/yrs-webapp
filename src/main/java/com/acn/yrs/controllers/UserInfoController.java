package com.acn.yrs.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acn.yrs.models.ResponseObject;
import com.acn.yrs.models.UserInfo;
import com.acn.yrs.services.LoginService;
import com.acn.yrs.utils.Helper;

@RestController
public class UserInfoController extends BaseController{


	Logger LOG = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	LoginService loginService;

	@Autowired
	Helper helper;

	@RequestMapping(value="/users", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<UserInfo> getUsers() {

		return null;
	}

	@RequestMapping(value="/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> login(@RequestBody UserInfo userInfo){

		LOG.debug("userId" + userInfo.getUserId());
		userInfo = loginService.login(userInfo);
		return getResponse(userInfo, userInfo.getTokenId(), userInfo.getHttpStatus());

	}

	@RequestMapping(value="/loadMain", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> loadMain(@RequestHeader String userId, @RequestHeader String tokenId){

		return checkUser(userId, tokenId);

	}
}
