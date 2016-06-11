package com.acn.yrs.services;

import java.nio.file.AccessDeniedException;

import com.acn.yrs.models.UserInfo;

public interface LoginService {

	public UserInfo login(UserInfo userInfo);
}
