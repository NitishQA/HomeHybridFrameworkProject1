package com.lms.qa.testcases;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lms.qa.base.BaseClass;
import com.lms.qa.pagesWebelements.LoginPageElements;
import com.lms.qa.utils.Utilities;


public class RegisterPage extends BaseClass
{
	
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
	public void teardown()
	{
		driver.quit();
		
	}
	
	@Test
	public void Verify_RegisterPageURL() throws InterruptedException
	{
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Register")).click();
	}
	
	@Test
	public void Verify_RegisterWithValidCredentials()
	{
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Tony");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Stark");
//		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("tony1@gipsy.com");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateEmailwithTimeStamp());		
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("11111");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
	}
	
	
}
