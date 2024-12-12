package com.ui.tests;
import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojo.User;
import com.utility.LoggerUtility;


@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest extends BaseTest{

	Logger logger=LoggerUtility.getLogger(this.getClass());
	
	/*Rules for creating a good test
	 * 1. Test should be small like 3,4 line maximum
	 * 2. You cannot have conditional statements ,loop, try catch in your test
	 * 3. Reduce the user of local variable.
	 * 4. Add at least one assertion in the test.
	 */
	

	/* DataProvider
	 * if dataProvider method is outside the class then you need to give the complete class name in the test with Package name 
	 */
	@Test(description="verify login with valid user", dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "loginTestDataProvider")
	public void loginTest(User user) {

		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName(), "Ankit Sharma");
}


	@Test(description="verify login with valid user", dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "loginTestCSVDataProvider")
	public void loginCSVTest(User user) {

		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName(), "Ankit Sharma");
}


//	@Test(description="verify login with valid user", dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "loginTestExcelDataProvider"
//			, retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class
//
//			)
//	public void loginExcelTest(User user) {
//
//		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName(), "Ankit Sharma");
//}









}
	