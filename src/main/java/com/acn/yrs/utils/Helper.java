package com.acn.yrs.utils;

import javax.ws.rs.core.Response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Helper {

	public static String PASSWORD="HindiLahatNgPogiNagaartistaYungIbaJavaDeveloper";
	public static String ALGO="PBEWITHSHA256AND128BITAES-CBC-BC";
	//public static String ALGO="PBEWithMD5AndTripleDES";



	public static Object packHeader(Object object, HttpStatus status){
		/*Response response = Response.status(status).
                entity(object).build();
		return response;*/

		return new ResponseEntity<Object>(
		          object, new HttpHeaders(), status);
	}

	public static Object packHeaderWithTokenId(Object object, String tokenId, HttpStatus status){
		if(tokenId==null) return packHeader(object, status);
		/*Response response = Response.status(status).
                entity(object).
                header("tokenId", tokenId).build();
		return response;*/
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("tokenId", tokenId);
		return new ResponseEntity<Object>(
		          object, httpHeaders , status);

	}
}
