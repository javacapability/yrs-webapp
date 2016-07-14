package com.acn.yrs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.SysParamWrapper;
import com.acn.yrs.models.SystemParameter;
import com.acn.yrs.repository.SystemParameterRepository;


@Service("systemParameterService")
@Transactional
public class SystemParameterServiceImpl implements SystemParameterService{
    
	
	@Autowired
	SystemParameterRepository sysParamRepository;
	
	@Override
	public List<SystemParameter> findAllByIsActive(int isActive) {
	     return null;
	}

	@Override
	public List<SystemParameter> create(SysParamWrapper systemParamList) {
		    
		List<SystemParameter> list = systemParamList.getParamList();
		sysParamRepository.save(list);
		return list;
		
	}

	@Override
	public List<SystemParameter> update(SysParamWrapper systemParamList) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
