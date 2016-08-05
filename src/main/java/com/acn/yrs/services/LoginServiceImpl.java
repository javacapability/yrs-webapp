package com.acn.yrs.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.AuditLog;
import com.acn.yrs.models.BaseConstants;
import com.acn.yrs.models.UserInfo;
import com.acn.yrs.repository.UserInfoRepository;
import com.acn.yrs.utils.Helper;
import com.acn.yrs.utils.Util;

@Service("loginService")
@Transactional
public class LoginServiceImpl  extends BaseConstants implements LoginService{

	@Autowired
	AuditLogService auditLogService;
	/**
	 * @return the auditLogService
	 */
	public AuditLogService getAuditLogService() {
		return auditLogService;
	}

	/**
	 * @param auditLogService the auditLogService to set
	 */
	public void setAuditLogService(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}


	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	Helper helper;

	Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Override
	public UserInfo login(UserInfo userInfo){


		LOG.info("Login Service()");

		AuditLog auditLog = auditLogService.saveTransaction(userInfo, SYSTEM_ACCESS_ACTION, AUDIT_TXN_SUCCESS, TXN_LOGIN_USER);
		//defaults
		UserInfo userInfoDB = userInfoRepository.findUserInfoByUserId(userInfo.getUserId().toUpperCase());
		if(userInfo.getPswd()!=null){
			if(userInfoDB!=null){
				if(!userInfo.getPswd().equals(Util.decode(userInfoDB.getPswd()))){
					LOG.info("Invalid Password");
					auditLog.setStatus(AUDIT_TXN_FAIL);
					auditLog.setReason(ERR_USERINFO_INVALID);
					userInfo = new UserInfo(HttpStatus.FORBIDDEN, HASERROR, ERR_USERINFO_INVALID);

				}else{
					LOG.info("Login Successful");
					userInfoDB.setTokenId(Util.getUUid());
					userInfoDB.setLastLogin(new Date());
					userInfoDB.setHttpStatus(HttpStatus.OK);
					userInfoDB.setResponseMsg(MSG_USER_LOGGED_IN);
					userInfo = userInfoDB;
				}
			}else{
				LOG.info("UserId not found in Database");
				auditLog.setStatus(AUDIT_TXN_FAIL);
				auditLog.setReason(ERR_USERINFO_NOTFOUND);
				userInfo = new UserInfo(HttpStatus.FORBIDDEN, HASERROR, ERR_USERINFO_NOTFOUND);
			}
		}else{
			LOG.info("UserId not found in Database");
			auditLog.setStatus(AUDIT_TXN_FAIL);
			auditLog.setReason(ERR_USERINFO_NOTFOUND);
			userInfo = new UserInfo(HttpStatus.FORBIDDEN, HASERROR, ERR_USERINFO_NOTFOUND);
		}

		auditLogService.updateTransaction(auditLog, userInfo);
		return userInfo;

	}

	/**
	 * @return the userInfoRepository
	 */
	public UserInfoRepository getUserInfoRepository() {
		return userInfoRepository;
	}

	/**
	 * @param userInfoRepository the userInfoRepository to set
	 */
	public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}

	/**
	 * @return the helper
	 */
	public Helper getHelper() {
		return helper;
	}

	/**
	 * @param helper the helper to set
	 */
	public void setHelper(Helper helper) {
		this.helper = helper;
	}


	// ***********************************************gene
		@Override
		public UserInfo  logout(UserInfo userInfo) {

			LOG.info("Logout Service()");
			AuditLog auditLog = new AuditLog();
			UserInfo userInfoDB = new UserInfo();
			try {
				userInfoDB = userInfoRepository.findUserInfoByUserId(userInfo.getUserId());
				auditLog = auditLogService.saveTransaction(userInfoDB, SYSTEM_ACCESS_ACTION, AUDIT_TXN_SUCCESS, TXN_LOGOUT_USER);
				if (userInfoDB != null) {
					userInfoDB.setTokenId(null);
					userInfo = userInfoRepository.save(userInfoDB);
					userInfo.setResponseMsg(MSG_USER_LOGGED_OUT);
					auditLog = auditLogService.updateTransaction(auditLog, userInfo);
				}else{
					auditLog = auditLogService.updateTransaction(auditLog, userInfo, AUDIT_TXN_FAIL, ERR_USERINFO_NOTFOUND);
					userInfo = new UserInfo(HttpStatus.FORBIDDEN, HASERROR, ERR_USERINFO_NOTFOUND);
					//userInfo.setResponseMsg(ERR_USERINFO_NOTFOUND);
				}
			} catch (Exception e) {
				auditLog = auditLogService.updateTransaction(auditLog, userInfo, AUDIT_TXN_FAIL, e.getMessage());
				userInfo.setErrorCd(HASERROR);
				userInfo.setErrorMsg(e.getMessage());
				//userInfo.setResponseMsg(e.getMessage());
				e.printStackTrace();
			}
			return userInfo;
		}

		// *****************************************************gene

}
