package com.acn.yrs.controllers;

import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acn.yrs.models.UserInfo;
import com.acn.yrs.services.LoginService;
import com.acn.yrs.utils.Helper;

@RestController
public class UserInfoController {


	Logger LOG = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	LoginService loginService;

	@RequestMapping(value="/users", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<UserInfo> getUsers() {

		return null;
	}

	@RequestMapping(value="/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public Object login(@RequestBody UserInfo userInfo){

		userInfo = loginService.login(userInfo);

		if(userInfo==null){
			return Helper.packHeader("User Info not found", HttpStatus.FORBIDDEN);
		}

		LOG.info("SessionId = " + userInfo.getTokenId());
		return Helper.packHeaderWithTokenId(userInfo, userInfo.getTokenId(), HttpStatus.OK);


	}

}
