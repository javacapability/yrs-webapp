package com.acn.yrs.services;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.AuditLog;
import com.acn.yrs.models.UserInfo;
import com.acn.yrs.repository.UserInfoRepository;
import com.acn.yrs.utils.BaseConstants;
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

		AuditLog auditLog = auditLogService.saveTransaction(userInfo,SAVE_ACTION, AUDIT_TXN_SUCCESS, TXN_CREATE_USER);
		try {

			UserInfo userInfoDB = userInfoRepository.findUserInfoByUserId(userInfo.getUserId().toUpperCase());
			if (userInfoDB == null) {
				userInfo.setUserId(userInfo.getUserId().toUpperCase());
				userInfo.setPswd(Util.encode(userInfo.getPswd()));
				userInfo.setErrorCd(HASNOERROR);
				userInfo = userInfoRepository.save(userInfo);
			}else{
				auditLog = auditLogService.updateTransaction(auditLog, userInfo,AUDIT_TXN_FAIL, ERR_USER_ID_EXISTS);
				userInfo.setErrorCd(HASERROR);
				userInfo.setErrorMsg(ERR_USER_ID_EXISTS);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			userInfo.setErrorCd(HASERROR);
			userInfo.setErrorMsg(e.getMessage());
			auditLog = auditLogService.updateTransaction(auditLog, userInfo,AUDIT_TXN_FAIL, e.getMessage());
		}
		return userInfo;
	}

	@Override
	public UserInfo update(UserInfo userInfo) {

		LOG.info("Update User Service()");

		UserInfo userInfoDB = userInfoRepository.findUserInfoByUserId(userInfo.getUserId().toUpperCase());
		AuditLog auditLog = auditLogService.saveTransaction(userInfoDB,UPDATE_ACTION, AUDIT_TXN_SUCCESS, TXN_UPDATE_USER);
		try {
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
				userInfoDB = userInfoRepository.save(userInfoDB);
				auditLog = auditLogService.updateTransaction(auditLog, userInfoDB);

			}else{
				userInfoDB = userInfo;
				userInfoDB.setErrorCd(HASERROR);
				userInfoDB.setErrorCd(ERR_USERINFO_NOTFOUND);
				auditLog = auditLogService.updateTransaction(auditLog, userInfoDB, AUDIT_TXN_FAIL, ERR_USERINFO_NOTFOUND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			userInfoDB.setErrorCd(HASERROR);
			userInfoDB.setErrorCd(e.getMessage());
			auditLog = auditLogService.updateTransaction(auditLog, userInfoDB, AUDIT_TXN_SUCCESS, e.getMessage());
		}
		return userInfoDB;
	}

	@Override
	public void delete(String userId) {

		LOG.info("Delete User Service()");
		UserInfo userInfoDB = userInfoRepository.findUserInfoByUserId(userId);

		AuditLog auditLog = auditLogService.saveTransaction(userInfoDB,DELETE_ACTION, AUDIT_TXN_SUCCESS, TXN_DELETE_USER);
		if (userInfoDB != null) {
			userInfoRepository.delete(userInfoDB);
		}else{

		}
	}

	@Override
	public List<UserInfo> getAllUsers() {
		// TODO Auto-generated method stub
		return userInfoRepository.findAll();
	}

	@Override
	public List<UserInfo> getAllUsersWithFilter(String userId, String fullName, String groupName) {
		// TODO Auto-generated method stub
		return userInfoRepository.findUserInfoByUserIdIgnoreCaseLikeOrFullNameIgnoreCaseLikeOrUserGroupGroupNameIgnoreCaseLike(userId, fullName, groupName);

	}

	@Override
	public List<UserInfo> getAllUsersExceptSelf(String userId, String fullName, String groupName, String adminUserId) {
		// TODO Auto-generated method stub
		return userInfoRepository.findUserInfoByUserIdIgnoreCaseLikeOrFullNameIgnoreCaseLikeOrUserGroupGroupNameIgnoreCaseLikeAndUserIdIgnoreCaseNot(userId, fullName, groupName, adminUserId);

	}

	@Override
	public Integer getIdbyUserId(String userId){
		return userInfoRepository.getIdByUserId(userId);
	}

	@Override
	public UserInfo findUserInfoByUserId(String userId){
		return userInfoRepository.findUserInfoByUserId(userId);
	}

	@Override
	public UserInfo findUserInfoById(int userUUID) {
		return userInfoRepository.findOne(userUUID);
	}
}

