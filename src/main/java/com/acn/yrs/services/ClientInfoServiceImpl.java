package com.acn.yrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.BaseConstants;
import com.acn.yrs.models.ClientInfo;
import com.acn.yrs.models.ResponseObject;
import com.acn.yrs.repository.ClientInfosRepository;
import com.acn.yrs.utils.Util;

@Service("clientInfoService")
@Transactional
public class ClientInfoServiceImpl extends BaseConstants implements ClientInfoService {

	@Autowired
	ClientInfosRepository clientInfosRepository;

	@Override
	public ClientInfo saveClientInfo(ClientInfo clientInfo) {
		return clientInfosRepository.save(clientInfo);
	}

	@Override
	public ClientInfo checkAndSaveClientInfo(ClientInfo clientInfo) {
		ClientInfo clientInfoDB = clientInfosRepository.findClientByClientNameAndBirthday(clientInfo.getClientName(), clientInfo.getBirthday());
		if(clientInfoDB!=null){
			clientInfoDB.setErrorCd(HASERROR);
			clientInfoDB.setErrorMsg(ERR_CLIENT_INFO_EXISTS);
		}else{
			if(Util.getAge(clientInfo.getBirthday())<18){
				clientInfoDB.setErrorCd(HASERROR);
				clientInfoDB.setErrorMsg(ERR_CLIENT_INFO_UNDERAGE);
			}
			clientInfoDB = clientInfosRepository.save(clientInfo);
			clientInfoDB.setErrorCd(HASNOERROR);
		}
		return clientInfoDB;
	}

}
