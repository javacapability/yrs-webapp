package com.acn.yrs.services;

import com.acn.yrs.models.UserInfo;

public interface LoginService {

	public UserInfo login(UserInfo userInfo);
	
	public void logout(String userid);
}
