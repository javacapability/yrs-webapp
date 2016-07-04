package com.acn.yrs.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.ErrorObject;
import com.acn.yrs.models.UserInfo;
import com.acn.yrs.repository.UserInfoRepository;
import com.acn.yrs.utils.Helper;
import com.acn.yrs.utils.Util;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	Helper helper;

	Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Override
	public Object login(UserInfo userInfo){

		LOG.info("Login Service()");

		//defaults
		String tokenId = null;
		Object obj = new Object();
		HttpStatus stat = HttpStatus.FORBIDDEN;

		UserInfo userInfoDB = userInfoRepository.findUserInfoByUserId(userInfo.getUserId().toUpperCase());
		if(userInfo.getPswd()!=null){
			if(userInfoDB!=null){
				if(!userInfo.getPswd().equals(Util.decode(userInfoDB.getPswd()))){
					LOG.info("Invalid Password");
					userInfoRepository.save(userInfoDB);
					obj = new ErrorObject(stat.toString(),"User Info is invalid");
				}else{
					LOG.info("Login Successful");
					tokenId = Util.getUUid();
					userInfoDB.setTokenId(tokenId);
					userInfoDB.setLastLogin(new Date());
					stat = HttpStatus.OK;
					obj = new UserInfo(userInfoDB);
				}
			}
		}else{
			LOG.info("UserId not found in Database");
			obj = new ErrorObject(stat.toString(),"User Info not found");

		}
		return helper.packResponseWithTokenId(obj, tokenId, stat);
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

}
