package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer{

	
	
//	private static final int MAX_TRY=Integer.parseInt( PropertiesUtil.readProperty(Env.DEV, "MAX_TRY")); // read value of max try from property file
	private static final int MAX_TRY= JSONUtility.readJson(Env.DEV).getMAX_TRY();// read value of max try from JSON file
	private static int count=0;
	@Override
	public boolean retry(ITestResult result) {
		if(count<MAX_TRY) {
			count++;
			return true;
		}
		return false;
	}

}
