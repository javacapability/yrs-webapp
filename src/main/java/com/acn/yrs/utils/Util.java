package com.acn.yrs.utils;

import java.util.UUID;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Util extends Helper{

	private StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	public Util() {

		encryptor.setPassword(PASSWORD);                     // we HAVE TO set a password
		//encryptor.setAlgorithm(ALGO);    // optionally set the algorithm

	}

	public String encode(String password){

		return encryptor.encrypt(password);
	}

	public String decode(String encryptedPassword){

		return encryptor.decrypt(encryptedPassword);

	}

	public static String getUUid(){
		UUID idOne = UUID.randomUUID();
	    return idOne.toString();

	}
}
