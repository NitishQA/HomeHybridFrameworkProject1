package com.lms.qa.testcases;


import java.io.ObjectInputFilter.Config;
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
import com.lms.qa.pagesWebelements.LandingPageWebelements;
import com.lms.qa.pagesWebelements.LoginPageElements;
import com.lms.qa.utils.Utilities;


public class LandingPageTest extends BaseClass
{
	
	public LandingPageTest()
	{
		super();  //We created super() , so that LandingPage can access the constructor of its super class i.e. BaseClass
	}
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
//		String browser = "chrome";
//		if(browser.equalsIgnoreCase("chrome"))
//		{
//			driver = new ChromeDriver();
//		}
//		else if(browser.equalsIgnoreCase("firefox"))
//		{
//			driver = new FirefoxDriver();
//		}
//		else if(browser.equalsIgnoreCase("edge"))
//		{
//			driver = new EdgeDriver();
//		}
		driver = intializeBrowserandOpenApplicationURL(conProp.getProperty("browserName"));
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(conProp.getProperty("url"));
		
	}
	
	@AfterMethod
	public void teardown() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.quit();
		
	}
	
	@Test (priority = 1)
	public void verifySearch_withValidProducts()
	{
		LandingPageWebelements LandingPageWebelements_Obj = new LandingPageWebelements(driver);
		LandingPageWebelements_Obj.enterInValidSearch(testdataProp.getProperty("validProductname"));
		LandingPageWebelements_Obj.click_on_searchbutton();
		System.out.println("Valid text is " +LandingPageWebelements_Obj.actual_ValidSearchText());
		Assert.assertTrue((LandingPageWebelements_Obj.actual_ValidSearchText()).contains(LandingPageWebelements_Obj.expected_ValidSearchText()), "Error message is wrong");
		
	}
	
	@Test (priority = 2)
	public void verifySearch_withInValidProducts()
	{
		LandingPageWebelements LandingPageWebelements_Obj = new LandingPageWebelements(driver);
		LandingPageWebelements_Obj.enterInValidSearch(testdataProp.getProperty("invalidProductname"));
		LandingPageWebelements_Obj.click_on_searchbutton();
		String actualInvalidProductText = LandingPageWebelements_Obj.actualinvalidProductText();
		String expectedInvalidTextMessage = LandingPageWebelements_Obj.expectedinvalidSearchText();
		Assert.assertTrue(actualInvalidProductText.contains(expectedInvalidTextMessage), "Error message is wrong");
	}
	
}
