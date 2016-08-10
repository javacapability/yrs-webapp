package com.acn.yrs.services;

import java.util.List;

import com.acn.yrs.models.UserInfo;

public interface UserService {
	public UserInfo create(UserInfo userInfo);

	public UserInfo update(UserInfo userInfo);

	public void delete(String userId);

	public List<UserInfo> getAllUsers();

	public Integer getIdbyUserId(String userId);

	public UserInfo findUserInfoByUserId(String userId);

	List<UserInfo> getAllUsersExceptSelf(String userId, String fullName, String groupName, String adminUserId);

	List<UserInfo> getAllUsersWithFilter(String userId, String fullName, String groupName);

	public UserInfo findUserInfoById(int userUUID);
}
