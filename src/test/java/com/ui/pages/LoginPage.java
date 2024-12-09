package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class LoginPage extends BrowserUtility {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
private static	final By EMAIL_TEXTBOX_LOCATOR =By.id("email");
private static	final By PASSWORD_TEXTBOX_LOCATOR =By.id("passwd");
private static	final By SIGN_IN_BUTTON_LOCATOR =By.id("SubmitLogin");
	
	public MyAccountPage doLoginWith(String email, String password) {
		enterText(EMAIL_TEXTBOX_LOCATOR, email);
		enterText(PASSWORD_TEXTBOX_LOCATOR, password);
		clicOn(SIGN_IN_BUTTON_LOCATOR);
		MyAccountPage map= new MyAccountPage(getDriver());
		return map;
		
	}

}
