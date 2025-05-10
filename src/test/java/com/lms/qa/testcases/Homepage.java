package com.lms.qa.testcases;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lms.qa.base.BaseClass;
import com.lms.qa.pagesWebelements.HomePageWebelements;
import com.lms.qa.pagesWebelements.LoginPageElements;
import com.lms.qa.utils.Utilities;


public class Homepage extends BaseClass
{
	LoginPageElements login_Page_ElementsObj;
	HomePageWebelements homePageWebelementsObj;
	
	
	public Homepage()
	{
		super();
	}
	public WebDriver driver;
	
	@BeforeMethod
	
	public void setup() throws InterruptedException
	{
//		loadPropertiesFile();// either we can call in this way, or create constructor of BaseClass and LoginPage and make as super 
		driver = intializeBrowserandOpenApplicationURL(conProp.getProperty("browserName"));
		
		login_Page_ElementsObj = new LoginPageElements(driver);
		login_Page_ElementsObj.enter_email(conProp.getProperty("validStudentUsername"));
		login_Page_ElementsObj.enter_password(conProp.getProperty("validStudentpassword"));
		login_Page_ElementsObj.click_on_LoginInButton();
		Thread.sleep(7000);
		
//		Thread.sleep(7000);

	}
	
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
//	@Test (enabled=false, dataProvider = "testvaliddataProvider1")
//	@Test (priority=1, dataProvider = "testvaliddataProvider1")
	@Test (priority=1)
	public void verify_HomePageOpening() throws InterruptedException
	{

//		login_Page_ElementsObj = new LoginPageElements(driver);
//		login_Page_ElementsObj.enter_username(prop.getProperty("validStudentUsername"));
//		login_Page_ElementsObj.enter_password(prop.getProperty("validStudentpassword"));
//		login_Page_ElementsObj.click_on_SignInButton();
//		Thread.sleep(7000);
//		WebDriverWait wait = new WebDriverWait(driver, 20);
		homePageWebelementsObj = new HomePageWebelements(driver);
		WebDriverWait webdwait = new WebDriverWait(driver, Duration.ofSeconds(60));
		webdwait.until(ExpectedConditions.visibilityOf(homePageWebelementsObj.getPastclassText()));
		
//		homePageWebelementsObj.currentClassVisiblity();
		homePageWebelementsObj.click_pastClass();
		
	}

	@Test (priority=2)
	public void verify_StudentHomeLogo() throws InterruptedException
	{

		
		homePageWebelementsObj = new HomePageWebelements(driver);
		homePageWebelementsObj.studentHomeLogo_isDisplayed();
		
	}
	
}
