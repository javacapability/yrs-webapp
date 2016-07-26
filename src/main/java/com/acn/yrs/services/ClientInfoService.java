package com.acn.yrs.services;

import com.acn.yrs.models.ClientInfo;
import com.acn.yrs.models.ResponseObject;

public interface ClientInfoService {

	public ClientInfo saveClientInfo(ClientInfo clientInfo);

	public ClientInfo checkAndSaveClientInfo(ClientInfo clientInfo);
}
