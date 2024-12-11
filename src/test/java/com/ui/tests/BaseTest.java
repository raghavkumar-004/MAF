package com.ui.tests;

import static com.constants.Browser.CHROME;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class BaseTest {
	protected HomePage homePage; //making this protected so that it can access in the child classes
	Logger logger=LoggerUtility.getLogger(this.getClass());
	
	
	private boolean isLambdaTest;
	
	@Parameters({"browser", "isLambdaTest", "isHeadless"})
	@BeforeMethod(description ="load the homepage of the website")
	public void setup(
	@Optional("firefox")	String browser,
	@Optional("false") boolean isLambdaTest,
	@Optional("false")boolean isHeadless,
	ITestResult result)
	{//Running the test in lambdaTest cloud
		WebDriver lambdaTestDriver;
		this.isLambdaTest=isLambdaTest;
		if(isLambdaTest) {
			lambdaTestDriver=LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName()); //it will return remote driver
			homePage= new HomePage(lambdaTestDriver);
		}
		else {
			//Running the test in local
		logger.info("load the homepage of the website");
		
		 homePage= new HomePage(Browser.valueOf(browser.toUpperCase()),isHeadless); // we can use static import for enum by doing this we don't need to user Browser.chrome we can directly use Chrome;

		}
	}
	
	/*
	 * below we are return the homePage but return type we are giving the parent class name which is BrowserUtility
	 * and we call this method in the onTestFailur method of screenshot to get the takeScreenshot method 
	 */
	public BrowserUtility getInstance() {
		return homePage;
	}
	
	
	@AfterMethod(description ="Tear down the browser")
	public void tearDown() {
	
	if(isLambdaTest) {
		LambdaTestUtility.quitSession(); //quit or close the browser session of lambdaTest 
	}
	
	else {
		
		homePage.quit();
		//write the code to close the local 
	}
	}
	
	
	
	
	
}
