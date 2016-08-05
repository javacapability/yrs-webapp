package com.acn.yrs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acn.yrs.models.UserInfo;
import com.acn.yrs.services.LoginService;
import com.acn.yrs.services.UserService;
import com.acn.yrs.utils.Helper;

@RestController
public class UserInfoController extends BaseController{


	Logger LOG = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	LoginService loginService;

	@Autowired
	UserService userService;

	@Autowired
	Helper helper;

	@RequestMapping(value="/users", method = RequestMethod.GET, headers = {"Accept=application/json"})
	public ResponseEntity<Object> getUsers() {

		return getResponse(userService.getAllUsers(),"",HttpStatus.OK);
	}


	@RequestMapping(value="/login", method = RequestMethod.POST, headers = {"Accept=application/json"})
	public ResponseEntity<Object> login(@RequestBody UserInfo userInfo){

		LOG.debug("userId" + userInfo.getUserId());
		userInfo.setAdminId(userInfo.getUserId());
		userInfo.setAppOrigin(APP_ORIGIN_MOBILE);
		userInfo = loginService.login(userInfo);
		return getResponse(userInfo, userInfo.getTokenId(), userInfo.getHttpStatus());

	}

	@RequestMapping(value="/loginWeb", method = RequestMethod.POST, headers = {"Accept=application/json"})
	public ResponseEntity<Object> loginWeb(@RequestBody UserInfo userInfo){

		LOG.debug("userId" + userInfo.getUserId());
		userInfo.setAdminId(userInfo.getUserId());
		userInfo.setAppOrigin(APP_ORIGIN_WEB);
		userInfo = loginService.login(userInfo);
		return getResponse(userInfo, userInfo.getTokenId(), userInfo.getHttpStatus());

	}

	@RequestMapping(value="/loadMain", method = RequestMethod.POST)
	public ResponseEntity<Object> loadMain(@RequestHeader String userId, @RequestHeader String tokenId){

		return checkUser(userId, tokenId);

	}

	// ************************************************gene
		/**
		 * This method is for logging user out in the application
		 *
		 * @param UserInfo
		 * @return
		 */
		@RequestMapping(value = "/logout", method = RequestMethod.POST, headers = "Accept=application/json")
		public ResponseEntity<Object> logout(@RequestHeader String userId, @RequestHeader String tokenId) {

			UserInfo userInfoLogout = new UserInfo();
			userInfoLogout.setUserId(userId);
			userInfoLogout.setAppOrigin(APP_ORIGIN_MOBILE);
			userInfoLogout.setAdminId(userId);

			LOG.debug("Logging Out User " + userId);
			ResponseEntity<Object> validity = checkUser(userId, tokenId);
			if (validity == null) {
				UserInfo userInfo = loginService.logout(userInfoLogout);
				return getResponse(userInfo, HttpStatus.OK);
			} else {
				return validity;
			}

		}


		@RequestMapping(value = "/logoutWeb", method = RequestMethod.POST, headers = "Accept=application/json")
		public ResponseEntity<Object> logoutWeb(@RequestHeader String userId, @RequestHeader String tokenId) {

			UserInfo userInfoLogout = new UserInfo();
			userInfoLogout.setUserId(userId);
			userInfoLogout.setAppOrigin(APP_ORIGIN_WEB);
			userInfoLogout.setAdminId(userId);

			LOG.debug("Logging Out User " + userId);
			ResponseEntity<Object> validity = checkUser(userId, tokenId);
			if (validity == null) {
				UserInfo userInfo = loginService.logout(userInfoLogout);
				return getResponse(userInfo, HttpStatus.OK);
			} else {
				return validity;
			}

		}
		/**
		 * This method is for admin user to create a user info
		 *
		 * @param UserInfo
		 * @return
		 */
		@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
		public ResponseEntity<Object> create(@RequestBody UserInfo userInfo, @RequestHeader String userId, @RequestHeader String tokenId) {

			LOG.debug("Register New User");
			ResponseEntity<Object> validity = checkUser(userId, tokenId);
			if (validity == null) {
				userInfo = userService.create(userInfo);
				return getResponse(userInfo, tokenId, HttpStatus.OK);
			} else {
				return validity;
			}


		}

		/**
		 * This method is for admin user to update a user info
		 *
		 * @param UserInfo
		 * @return
		 */

		@RequestMapping(value = "/update", method = RequestMethod.POST, headers = "Accept=application/json")
		public ResponseEntity<Object> update(@RequestBody UserInfo userInfo,
				@RequestHeader String userId, @RequestHeader String tokenId) {

			LOG.debug("Update User Details");
			ResponseEntity<Object> validity = checkUser(userId, tokenId);
			if (validity == null) {
				userInfo = userService.update(userInfo);
				return getResponse(userInfo,tokenId, HttpStatus.OK);
			} else {
				return validity;
			}

		}

		@RequestMapping(value = "/delete", method = RequestMethod.POST, headers = "Accept=application/json")
		public ResponseEntity<Object> delete(@RequestBody UserInfo userInfo,
				@RequestHeader String userId, @RequestHeader String tokenId) {


			LOG.debug("Delete User Details");
			ResponseEntity<Object> validity = checkUser(userId,
					tokenId);
			if (validity == null) {
				userService.delete(userInfo.getUserId());
				return getResponse("User Deleted", HttpStatus.OK);
			} else {
				return validity;
			}

		}

		// ************************************************gene

}
