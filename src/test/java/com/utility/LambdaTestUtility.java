package com.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtility {

	
	  
	private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub"; // it is constant that's why we make this final
	private static final ThreadLocal<WebDriver> driverLocal= new ThreadLocal<WebDriver>();
	private static final ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<DesiredCapabilities>();
	
	
	public static WebDriver initializeLambdaTestSession(String browserName, String testName)
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", "127");
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "rk8262856004");
        ltOptions.put("accessKey", "xJE2o3k3YNR0sIh2ab2gjoS0xKTWrg2s9glleqqaNPsOBoLS7o");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.23.0");
        capabilities.setCapability("LT:Options", ltOptions);
        capabilitiesLocal.set(capabilities); //attach capabilities object with capabilitiesLocal(ThreadLocal of Capabilities object)
        WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get());//Run the test remotely 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driverLocal.set( driver);
       return driverLocal.get();
	}

public static void quitSession() {
	if(driverLocal.get()!=null) {
		driverLocal.get().quit(); 
	}
}













}
