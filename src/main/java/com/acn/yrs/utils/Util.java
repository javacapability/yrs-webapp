package com.acn.yrs.utils;

import java.util.UUID;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Util extends Helper{

	private static StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

	static {
		encryptor.setPassword(PASSWORD);                     // we HAVE TO set a password
	}


	public static String encode(String password){

		return encryptor.encrypt(password);
	}

	public static String decode(String encryptedPassword){

		return encryptor.decrypt(encryptedPassword);

	}

	public static String getUUid(){
		UUID idOne = UUID.randomUUID();
	    return idOne.toString();

	}


	/*public static void main(String[] args) {

		String value = "value=ngOJgPMGz/MrLXta4gABlA==";
		if (value.contains("="))
		{
			String[] attrArray = value.split("=");
			for(String x:attrArray){
				System.out.println(x);
			}
		}

	}*/
}
