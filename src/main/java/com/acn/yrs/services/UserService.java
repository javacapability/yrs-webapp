package com.acn.yrs.services;

import com.acn.yrs.models.UserInfo;

public interface UserService {
	public UserInfo create(UserInfo userInfo);

	public UserInfo update(UserInfo userInfo);

	public void delete(String userId);
}
