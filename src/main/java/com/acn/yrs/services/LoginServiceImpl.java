package com.acn.yrs.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.BaseConstants;
import com.acn.yrs.models.UserInfo;
import com.acn.yrs.repository.UserInfoRepository;
import com.acn.yrs.utils.Helper;
import com.acn.yrs.utils.Util;

@Service("loginService")
@Transactional
public class LoginServiceImpl  extends BaseConstants implements LoginService{

	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	Helper helper;

	Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Override
	public UserInfo login(UserInfo userInfo){

		LOG.info("Login Service()");

		//defaults
		UserInfo userInfoDB = userInfoRepository.findUserInfoByUserId(userInfo.getUserId().toUpperCase());
		if(userInfo.getPswd()!=null){
			if(userInfoDB!=null){
				if(!userInfo.getPswd().equals(Util.decode(userInfoDB.getPswd()))){
					LOG.info("Invalid Password");
					//
					//
					//userInfoRepository.save(userInfoDB);
					return new UserInfo(HttpStatus.FORBIDDEN, HASERROR, USERINFO_INVALID);

				}else{
					LOG.info("Login Successful");
					userInfoDB.setTokenId(Util.getUUid());
					userInfoDB.setLastLogin(new Date());
					userInfoDB.setHttpStatus(HttpStatus.OK);
					return userInfoDB;
				}
			}
		}else{
			LOG.info("UserId not found in Database");
			return new UserInfo(HttpStatus.FORBIDDEN, HASERROR, USERINFO_NOTFOUND);
		}
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
		public void logout(String userid) {
			// TODO Auto-generated method stub
			LOG.info("Logout Service()");

			UserInfo userInfo = userInfoRepository.findUserInfoByUserId(userid);
			if (userInfo != null) {
				userInfo.setTokenId(null);
				userInfoRepository.save(userInfo);
			}

		}
		// *****************************************************gene

}
