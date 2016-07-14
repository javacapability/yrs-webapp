package com.acn.yrs.controllers;

import java.util.List;

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

import com.acn.yrs.models.SysParamWrapper;
import com.acn.yrs.models.SystemParameter;
import com.acn.yrs.services.SystemParameterService;
import com.acn.yrs.utils.Helper;
@RestController
public class SysParamController extends BaseController {

	Logger LOG = LoggerFactory.getLogger(SysParamController.class);

	@Autowired
	SystemParameterService sysParamService;

	@Autowired
	Helper helper;

	@RequestMapping(value = "/syspar/create", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> create(@RequestHeader String userId,
			@RequestBody SysParamWrapper systemParamInfo,
			@RequestHeader String tokenId) {
		
		
		ResponseEntity<Object> validity = checkUser(userId, tokenId);
		if (validity == null) {
			List<SystemParameter> list = sysParamService.create(systemParamInfo);
			return getResponse(list,tokenId, HttpStatus.OK);
		} else {
			return validity;
		}
	}

	@RequestMapping(value = "/syspar/update", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> update(@RequestHeader String userId,
			@RequestBody SysParamWrapper systemParamInfo,
			@RequestHeader String tokenId) {
		ResponseEntity<Object> validity = checkUser(userId, tokenId);
		if (validity == null) {
			List<SystemParameter> list = sysParamService.update(systemParamInfo);
			return getResponse(list,tokenId, HttpStatus.OK);
		} else {
			return validity;
		}
	}

	@RequestMapping(value = "/syspar/all", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> getAll(@RequestHeader String userId,
			@RequestHeader String tokenId) {

		ResponseEntity<Object> validity = checkUser(userId, tokenId);
		if (validity == null) {
			List<SystemParameter> list = sysParamService.findAllByIsActive(1);
			return getResponse(list,tokenId, HttpStatus.OK);
		} else {
			return validity;
		}
	}

	public SystemParameterService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(SystemParameterService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public Helper getHelper() {
		return helper;
	}

	public void setHelper(Helper helper) {
		this.helper = helper;
	}

}
