package com.acn.yrs.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.UserInfo;

@RepositoryRestResource(path="userInfos")
public interface UserInfoRepository extends PagingAndSortingRepository<UserInfo, Integer> {

	public List<UserInfo> findAll();

	public UserInfo findUserInfoByUserId(String userId);

	public Integer getIdByUserId(String userId);
}
