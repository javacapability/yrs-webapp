package com.acn.yrs.services;

import com.acn.yrs.models.UserInfo;

public interface LoginService {

	public UserInfo login(UserInfo userInfo);

	public UserInfo logout(String userid);
}
