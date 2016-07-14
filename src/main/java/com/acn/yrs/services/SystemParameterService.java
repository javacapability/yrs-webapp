package com.acn.yrs.services;

import java.util.List;

import com.acn.yrs.models.SysParamWrapper;
import com.acn.yrs.models.SystemParameter;

public interface SystemParameterService {
	
	List<SystemParameter> findAllByIsActive(int isActive);
	List<SystemParameter> create(SysParamWrapper systemParamList);
	List<SystemParameter> update(SysParamWrapper systemParamList);
}
