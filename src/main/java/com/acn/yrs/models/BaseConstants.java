package com.acn.yrs.models;

import org.springframework.http.HttpStatus;

public class BaseConstants {

	public static final String HASERROR = "1";
	public static final String HASNOERROR = "0";
	public static final String INVALID_SESSION = "Invalid session";
	public static final String SESSION_EXPIRED = "Session has expired";
	public static final String AUTH_FAILED = "Authentication failed";
	public static final String USERINFO_NOTFOUND = "User Info not found";
	public static final String USERINFO_INVALID = "User Info is invalid";
	public static final String NORECORDFOUND = "No Records Found";

	public static final int ASSESSMENTPENDING = 1;
	public static final int ASSESSMENTRECOGIVEN = 2;
	public static final int ASSESSMENTARCHIVED = 3;

	public static final String ASSESSMENTNOTFOUND = "Assessment not found";
	public static final String ASSESSMENT_ERROR_PAYLOAD_NULL = "Assessment payload is null";
	public static final String CLIENT_INFO_EXISTS = "Client already exists";
	public static final String CLIENT_INFO_UNDERAGE = "Client should be 18 years old and above";

	public static final String NO_QUESTIONS_FOUND="No Questions Found";

	public static final String UPDATE_ACTION = "UPDATE";
	public static final String SAVE_ACTION = "INSERT";
	public static final String DELETE_ACTION = "DELETE";

	public static final String AUDIT_TXN_SUCCESS = "Success";
	public static final String AUDIT_TXN_FAIL = "Failed";

	public static final String TXN_CREATE_QUESTION = "Create New Question";
	public static final String TXN_UPDATE_QUESTION = "Update Question";
	public static final String TXN_DELETE_QUESTION = "Delete Question";
	public static final String TXN_CREATE_USER = "Create New User";
	public static final String TXN_UPDATE_USER = "Update User";
	public static final String TXN_DELETE_USER = "Delete User";



	public BaseConstants() {
		super();
	}

}