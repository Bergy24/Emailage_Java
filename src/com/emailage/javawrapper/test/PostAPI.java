package com.emailage.javawrapper.test;

import java.io.IOException;

import com.emailage.javawrapper.model.Enums;
import com.emailage.javawrapper.model.Enums.Environment;
import com.emailage.javawrapper.model.ExtraInputParameter;
import com.emailage.javawrapper.utilities.EmailageClient;

public class PostAPI {

	/**
	 * @param environment 
	 * @param args
	 *            the command line arguments
	 */
	public static String callEmailAge(String env, String email, String ip, String firstName, String lastName, String address, String city, String state, String zip, String phone)	{
		/* Results can be in JSON or XML format */
		Enums.Format resultFormat = Enums.Format.Xml;
		Enums.SignatureMethod signatureMethod = Enums.SignatureMethod.HMAC_SHA256;

		/*
		 * OPTIONAL FIELD. Use this field if you want to associate the API call
		 * to a specific Emailage user.
		 */
		String user_email = null;
		
		Enums.Environment environment = Enums.Environment.Production;
		
		ExtraInputParameter extraArgs = new ExtraInputParameter();
		extraArgs.setfirstname(firstName);
		extraArgs.setlastname(lastName);
		extraArgs.setbilladdress(address);
		extraArgs.setbillcity(city);
		extraArgs.setbillregion(state);
		extraArgs.setbillpostal(zip);
		extraArgs.setphone(phone);

		String validResult = EmailageClient.QueryValidParams(email, ip, extraArgs,  resultFormat, signatureMethod, user_email, environment);
		return validResult.substring(1);

	}
		
	
	public static void main(String[] args) {
		System.out.println(callEmailAge("Production","COREY.CAUDILL@gmail.com","","COREY","CAUDILL","","","","29020",""));
	}

}
