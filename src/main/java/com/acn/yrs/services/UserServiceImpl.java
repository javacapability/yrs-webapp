package com.acn.yrs.services;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.BaseConstants;
import com.acn.yrs.models.UserInfo;
import com.acn.yrs.repository.UserInfoRepository;
import com.acn.yrs.utils.Helper;
import com.acn.yrs.utils.Util;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseConstants implements UserService {

	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	Helper helper;

	@Autowired
	AuditLogService auditLogService;

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
	public UserInfo create(UserInfo userInfo) {
		// defaults
		LOG.info("Create User Service()");

		UserInfo userInfoDB = userInfoRepository.findUserInfoByUserId(userInfo
				.getUserId().toUpperCase());
		if (userInfoDB == null) {
			userInfo.setPswd(Util.encode(userInfo.getPswd()));
			userInfo.postSaveOrUpdate();
			userInfoRepository.save(userInfo);

			auditLogService.saveTransaction(userInfo.getAuditLog(),
					SAVE_ACTION, AUDIT_TXN_SUCCESS, TXN_CREATE_USER);
		}
		return userInfo;
	}

	@Override
	public UserInfo update(UserInfo userInfo) {

		LOG.info("Update User Service()");

		UserInfo userInfoDB = userInfoRepository.findUserInfoByUserId(userInfo
				.getUserId().toUpperCase());
		userInfoDB.preSaveOrUpdate();
		if (userInfoDB != null) {
			// set new values from UserInfo entity
			userInfoDB.setBirthday(userInfo.getBirthday());
			userInfoDB.setFullName(userInfo.getFullName());
			userInfoDB.setEmail(userInfo.getEmail());
			userInfoDB.setUpDt(new Date());

			// encode the new password
			if (!userInfo.getPswd().equals(Util.decode(userInfoDB.getPswd()))) {
				userInfoDB.setPswd(Util.encode(userInfo.getPswd()));
			}
			userInfoDB.postSaveOrUpdate();
			userInfoRepository.save(userInfoDB);

			auditLogService.saveTransaction(userInfoDB.getAuditLog(),
					UPDATE_ACTION, AUDIT_TXN_SUCCESS, TXN_UPDATE_USER);
		}
		return userInfoDB;
	}

	@Override
	public void delete(String userId) {

		LOG.info("Delete User Service()");
		UserInfo userInfoDB = userInfoRepository.findUserInfoByUserId(userId);

		userInfoDB.preSaveOrUpdate();


		if (userInfoDB != null) {
			userInfoRepository.delete(userInfoDB);
		}

		auditLogService.saveTransaction(userInfoDB.getAuditLog(),DELETE_ACTION, AUDIT_TXN_SUCCESS, TXN_DELETE_USER);
	}

	@Override
	public List<UserInfo> getAllUsers() {
		// TODO Auto-generated method stub
		return userInfoRepository.findAll();
	}

	@Override
	public Integer getIdbyUserId(String userId){
		return userInfoRepository.getIdByUserId(userId);
	}

	@Override
	public UserInfo findUserInfoByUserId(String userId){
		return userInfoRepository.findUserInfoByUserId(userId);
	}
}

