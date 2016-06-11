package com.acn.yrs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.UserInfo;

@RepositoryRestResource
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {

	public List<UserInfo> findAll();

	public UserInfo findUserInfoByUserId(String userId);
}
