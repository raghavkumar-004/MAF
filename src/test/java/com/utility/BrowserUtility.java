package com.utility;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {
	Logger logger=LoggerUtility.getLogger(this.getClass());
	
	/*
	 * BrowserUtiity is the parent class of page classes so parent classes are always going to mark with abstract keywords
	 * No other class can be the child of BrowserUtility because it has selenium methods and we use selenium method only in page class
	 * making this driver thread local because for every test new browser is launched and driver is responsible for handling new window so we make it thread local
	 * After making it thread local we can use get() and set() method to get and set the value 
	 */
	
	private static ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>(); ////

	public  WebDriver getDriver() {
		return driver.get();
	}


	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver); //initialize the instance variable driver;
		
	}
	public BrowserUtility(String browserName) {
		logger.info("launching browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver()) ;
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver.set( new FirefoxDriver());
		}else if(browserName.equalsIgnoreCase("edge"))
		{
			driver.set(new EdgeDriver());
		}
		else {
			logger.error("please select valid browser....e.g chrome, firefox or edge");
			System.err.println("please select valid browser....e.g chrome, firefox or edge");
		}
	}

	public BrowserUtility(Browser browserName) {
		logger.info("launching browser");
		if(browserName==Browser.CHROME) {
			driver.set(new ChromeDriver());
		}else if(browserName==Browser.FIREFOX)
		{
			driver.set(new FirefoxDriver());
		}else if(browserName==Browser.EDGE)
		{
			driver.set(new  EdgeDriver());
		}	
}
	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("launching browser");
		if(browserName==Browser.CHROME) {
			if(isHeadless) {
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--headless=old--");//launch driver in headless mode
			options.addArguments("--window-size=1920--");//in headless mode this is the window size
			driver.set(new ChromeDriver(options));
			}
			else {
				driver.set(new ChromeDriver());
			}
		}else if(browserName==Browser.FIREFOX)
		{
			if(isHeadless) {
				FirefoxOptions options= new FirefoxOptions();
				options.addArguments("--headless=old");//launch driver in headless mode
				options.addArguments("--window-size=1920--");//in headless mode this is the window size
				driver.set(new FirefoxDriver(options));
				}
			else {
			driver.set(new FirefoxDriver());
			}
		}else if(browserName==Browser.EDGE)
		{
			if(isHeadless) {
			EdgeOptions options= new EdgeOptions();
			options.addArguments("--headless=old");//launch driver in headless mode
			options.addArguments("disable-gpu");//in headless mode this is the window size
			driver.set(new EdgeDriver(options));
			}
			driver.set(new  EdgeDriver());
		}	
}
	
	public void goToWebsite(String url) {
		logger.info("visiting the website "+ url);
		driver.get().get(url);

	}
	public void maximizeWindow() {
		logger.info("Mazimizing the browser window");
		driver.get().manage().window().maximize();
	}
	public void clicOn(By locator)
	{		logger.info("Finding the element with locator"+ locator);
		WebElement element= driver.get().findElement(locator);
		logger.info("Element found and now performing click ");
		element.click();
	}
	
	public void enterText(By locator, String textToEnter) {
		logger.info("Finding the element with locator"+ locator);
		WebElement element= driver.get().findElement(locator);
		logger.info("Element found and now enter text  "+ textToEnter);
		element.sendKeys(textToEnter);
	}

	public String getVisibleText(By locator) {
		logger.info("Finding the element with locator"+ locator);
		WebElement element= driver.get().findElement(locator);
		logger.info("Element found and now return the visible value" + element.getText());
	return	element.getText();
	}
	
	
	public String takeScreenshot(String name) {
	TakesScreenshot ts= (TakesScreenshot)driver.get();
	
	Date date= new Date();
	SimpleDateFormat format= new SimpleDateFormat("HH-mm-ss");
	String timeStamp= format.format(date);
	//String path = System.getProperty("user.dir") + "/screenshots/" + name + " - " + timeStamp + ".png";
	//String path=System.getProperty("user.dir")+"//screenshots//" +name+" - "+timeStamp+".png";
	String path =  "./screenshots/" + name + "  -  " + timeStamp + ".png";
	File sorce=ts.getScreenshotAs(OutputType.FILE);
	File des= new File(path);
	try {
		FileUtils.copyFile(sorce, des);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return path;
	}
	
	public void quit() {
		driver.get().quit();
	}
}
