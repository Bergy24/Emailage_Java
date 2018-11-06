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
		
		Enums.Environment environment;
		
		if (env=="Production")	{
			environment = Enums.Environment.Production;
		}else{
			environment = Enums.Environment.Sandbox;
		}
		
		if (ip=="" || ip==null)	{

			// One value present
			try {
				System.out.println("Querying Email");
				
				ExtraInputParameter extraArgs = new ExtraInputParameter();
				extraArgs.setfirstname(firstName);
				extraArgs.setlastname(lastName);
				extraArgs.setbilladdress(address);
				extraArgs.setbillcity(city);
				extraArgs.setbillregion(state);
				extraArgs.setbillpostal(zip);
				extraArgs.setphone(phone);

				String validResult = EmailageClient.QueryEmail(email, extraArgs, resultFormat, signatureMethod, user_email, environment);

				return validResult;
				

			} catch (IOException e) {
				e.printStackTrace();
				return "err";
			}
			
			
		}else if (email=="" || email==null)	{

			// One value present
			try {
				System.out.println("Querying IP");
				
				ExtraInputParameter extraArgs = new ExtraInputParameter();
				extraArgs.setfirstname(firstName);
				extraArgs.setlastname(lastName);
				extraArgs.setbilladdress(address);
				extraArgs.setbillcity(city);
				extraArgs.setbillregion(state);
				extraArgs.setbillpostal(zip);
				extraArgs.setphone(phone);

				String validResult = EmailageClient.QueryEmail(ip, extraArgs, resultFormat, signatureMethod, user_email, environment);

				return validResult;

			} catch (IOException e) {
				e.printStackTrace();
				return "err";
			}
		
		}else{

			// Email + IP validation
			try {
			System.out.println("Querying Email + IP");
			
			ExtraInputParameter extraArgs = new ExtraInputParameter();
			extraArgs.setfirstname(firstName);
			extraArgs.setlastname(lastName);
			extraArgs.setbilladdress(address);
			extraArgs.setbillcity(city);
			extraArgs.setbillregion(state);
			extraArgs.setbillpostal(zip);
			extraArgs.setphone(phone);

			String validResult = EmailageClient.QueryEmailAndIPPlusExtraArgs(email, ip, extraArgs,  resultFormat,
					signatureMethod, user_email, environment);

			return validResult;

			} catch (IOException e) {
			e.printStackTrace();
			return "err";
			}
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(callEmailAge("Production","COREY.CAUDILL@gmail.com","","COREY","CAUDILL","","","","29020",""));
	}

}
