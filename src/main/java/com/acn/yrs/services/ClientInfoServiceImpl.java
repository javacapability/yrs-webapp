package com.acn.yrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.ClientInfo;
import com.acn.yrs.repository.ClientInfosRepository;

@Service("clientInfoService")
@Transactional
public class ClientInfoServiceImpl implements ClientInfoService {

	@Autowired
	ClientInfosRepository clientInfosRepository;

	@Override
	public ClientInfo saveClientInfo(ClientInfo clientInfo) {
		return clientInfosRepository.save(clientInfo);
	}

}
