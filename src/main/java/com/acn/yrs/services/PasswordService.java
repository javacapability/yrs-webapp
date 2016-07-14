package com.acn.yrs.services;

import com.acn.yrs.models.UserInfo;

public interface PasswordService {
     
	   public UserInfo resetPassword(String userId, String password);
	  
}
