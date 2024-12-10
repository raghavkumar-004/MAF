package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

import static com.utility.PropertiesUtil.*;

import static com.utility.JSONUtility.*;


public final class HomePage extends BrowserUtility {
	Logger logger=LoggerUtility.getLogger(this.getClass());
	
public HomePage(Browser browserName, boolean isHeadless) {
	
		super(browserName,isHeadless);//call parent class constructor
	    goToWebsite(readProperty(DEV, "URL"));//reading data from the property file
		maximizeWindow();
	//	goToWebsite(JSONUtility.readJson(QA).getMAX_TRY()); //reading data from the json file
	}
public HomePage(WebDriver driver) {
	super(driver);//
	 goToWebsite(readProperty(DEV, "URL"));
	 maximizeWindow();
}

/*
 * Rule for page class
 * 1. It should store the variable type locators
 * 2. Locators are constants because we are not going to change the value so we need to declare constant with Final keyword
 * 3. In java it is rule to add static keyword for constants and we call this as class variable because static variable are class variable
 * 4. Constant name should be in upper case
 * 5. child class need to declare with final keyword
 * 6. abstract class can have constructor and that constructor is called in the child class because we cannot create instance of abstract class.
 * 7. 
 */

private static final	By EMAIL_TEXT_LOCATOR= By.xpath("//a[contains(text(), 'Sign in')]"); 



public LoginPage goToLoginPage() { // this is page functions and we cannot use void as return type for page function 
	//we return the next page object in the page function
	logger.info("trying to perform click operation on the sign in link to go to sign in page");
	clicOn(EMAIL_TEXT_LOCATOR);
	LoginPage lPage= new LoginPage(getDriver());
	return lPage;
}

}
