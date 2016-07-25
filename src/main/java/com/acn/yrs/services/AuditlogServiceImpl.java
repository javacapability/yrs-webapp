package com.acn.yrs.services;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.AuditLog;
import com.acn.yrs.repository.AuditLogsRepository;

@Service("auditLogService")
@Transactional
public class AuditlogServiceImpl implements AuditLogService {

	@Autowired
	AuditLogsRepository auditLogRepository;

	@Override
	public void saveTransaction(AuditLog auditLog, int action) {
        
		auditLog.setAction(action);
		auditLog.setStatus(1);
		auditLog.setDateTime(new Date());
		auditLogRepository.save(auditLog);
	}

}
