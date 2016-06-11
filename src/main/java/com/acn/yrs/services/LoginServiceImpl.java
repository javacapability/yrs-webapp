package com.acn.yrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acn.yrs.models.UserInfo;
import com.acn.yrs.repository.UserInfoRepository;
import com.acn.yrs.utils.Util;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserInfoRepository userInfoRepository;

	@Override
	public UserInfo login(UserInfo userInfo){

		Util util = new Util();

		UserInfo userInfoDB = userInfoRepository.findUserInfoByUserId(userInfo.getUserId().toUpperCase());
		if(userInfo.getPswd()!=null){
			if(userInfoDB!=null){
				if(!userInfo.getPswd().equals(util.decode(userInfoDB.getPswd()))){
					userInfoDB.setTokenId(null);
					userInfoRepository.save(userInfoDB);
					userInfoDB = null;
				}else{
					userInfoDB.setTokenId(Util.getUUid());
				}
			}
		}else{
			userInfoDB = null;
		}

		return userInfoDB;
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

}
