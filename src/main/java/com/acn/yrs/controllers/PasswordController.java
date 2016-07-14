package com.acn.yrs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acn.yrs.models.UserInfo;
import com.acn.yrs.services.PasswordService;
import com.acn.yrs.utils.Helper;

@RestController
public class PasswordController extends BaseController {

	Logger LOG = LoggerFactory.getLogger(PasswordController.class);

	@Autowired
	PasswordService passwordService;

	@Autowired
	Helper helper;

	@RequestMapping(value = "/reset", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> reset(@RequestBody UserInfo userInfo,
			@RequestHeader String tokenId) {

		LOG.debug("userId" + userInfo.getUserId());

		ResponseEntity<Object> validity = checkUser(userInfo.getUserId(),
				tokenId);
		if (validity == null) {
			userInfo = passwordService.resetPassword(userInfo.getUserId(),
					userInfo.getPswd());
			return getResponse(userInfo, HttpStatus.OK);
		} else {
			return validity;
		}

	}

	public PasswordService getPasswordService() {
		return passwordService;
	}

	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	public Helper getHelper() {
		return helper;
	}

	public void setHelper(Helper helper) {
		this.helper = helper;
	}

}
