package com.acn.yrs.services;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.AuditLog;
import com.acn.yrs.models.ResponseObject;
import com.acn.yrs.repository.AuditLogsRepository;
import com.acn.yrs.repository.UserInfoRepository;

@Service("auditLogService")
@Transactional
public class AuditlogServiceImpl implements AuditLogService {

	@Autowired
	AuditLogsRepository auditLogRepository;
	@Autowired
	UserInfoRepository userInfoRepository;

	@Override
	public AuditLog saveTransaction(ResponseObject object, String action, String status, String function) {
		if(object==null) object= new ResponseObject();
		AuditLog auditLog = new AuditLog();
		auditLog.setUserInfo(userInfoRepository.findUserInfoByUserId(object.getAdminId()));
		auditLog.setApp(object.getAppOrigin());
		auditLog.setAction(action);
		auditLog.setStatus(status);
		auditLog.setFunction(function);
		auditLog.setDateTime(new Date());
		auditLog.setOldTxnDtls(object.toString());
		return auditLogRepository.save(auditLog);

	}

	@Override
	public AuditLog updateTransaction(AuditLog auditLog, ResponseObject object, String status,
			String reason) {
		if(object==null) object= new ResponseObject();
		auditLog.setNewTxnDtls(object.toString());
		auditLog.setStatus(status);
		auditLog.setReason(reason);
		return auditLogRepository.save(auditLog);
	}

	@Override
	public AuditLog updateTransaction(AuditLog auditLog, ResponseObject object) {
		if(object==null) object= new ResponseObject();
		auditLog.setNewTxnDtls(object.toString());
		return auditLogRepository.save(auditLog);
	}
}
