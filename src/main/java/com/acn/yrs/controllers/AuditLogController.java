package com.acn.yrs.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acn.yrs.models.AuditLog;
import com.acn.yrs.repository.AuditLogsRepository;

@RestController
public class AuditLogController extends BaseController{
	
	Logger LOG = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	AuditLogsRepository auditLogsRepository;

	@RequestMapping(value="/audit/all", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getAuditLogs(@RequestHeader String userId,
			@RequestHeader String tokenId) {
		
		ResponseEntity<Object> validity = checkUser(userId,
				tokenId);
		if (validity == null) {
			 List<AuditLog> auditLogs = (List<AuditLog>) auditLogsRepository.findAll();
			 return getResponse(auditLogs,tokenId, HttpStatus.OK);
		} else {
			return validity;
		}
	}


}
