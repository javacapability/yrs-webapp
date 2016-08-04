package com.acn.yrs.services;

import com.acn.yrs.models.AuditLog;
import com.acn.yrs.models.ResponseObject;

public interface AuditLogService {

	public AuditLog saveTransaction(ResponseObject object, String action, String status, String function);
	public AuditLog updateTransaction(AuditLog auditLog, ResponseObject object, String status, String reason);
	public AuditLog updateTransaction(AuditLog auditLog, ResponseObject object);

}
