package com.acn.yrs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.UserInfo;
import com.acn.yrs.repository.UserInfoRepository;
import com.acn.yrs.utils.Helper;
import com.acn.yrs.utils.Util;

@Service("passwordService")
@Transactional
public class PasswordServiceImpl implements PasswordService {

	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	Helper helper;

	Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);

	public UserInfoRepository getUserInfoRepository() {
		return userInfoRepository;
	}

	public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}

	public Helper getHelper() {
		return helper;
	}

	public void setHelper(Helper helper) {
		this.helper = helper;
	}

	@Override
	public UserInfo resetPassword(String userId, String password) {

		UserInfo userInfo = userInfoRepository.findUserInfoByUserId(userId);
		userInfo.setTokenId(null);
		userInfo.setPswd(Util.encode(password));
		userInfoRepository.save(userInfo);
		return userInfo;
	}

}
