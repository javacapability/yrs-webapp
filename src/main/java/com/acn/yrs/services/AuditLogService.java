package com.acn.yrs.services;

import com.acn.yrs.models.AuditLog;

public interface AuditLogService {
 
	public void saveTransaction(AuditLog auditLog, int action);
	    
}
