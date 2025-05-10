package com.lms.qa.testcases;


import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lms.qa.base.BaseClass;
import com.lms.qa.pagesWebelements.LoginPageElements;
import com.lms.qa.utils.Utilities;


public class LoginPageTest extends BaseClass  //Now LoginPage can use the methods , variable of BaseClass
{
	
//	Since LoginPage extends BaseClass , so we can create a super() constructor so that can access the BaseClass(Super class of Login) constructor
	public LoginPageTest()
	{
		super();  //We created super() , so that LoginPage can access the constructor of its super class i.e. BaseClass
	}
	
	WebDriver driver;
	
	@DataProvider (name = "testvaliddataProvider1")
	public Object[][] supplyTestData()
	{
		Object[][] validcredentialsdata = Utilities.getTestdatafromExcel("loginpagesheetValidCredentials");
		return validcredentialsdata;
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
	public void teardown()
	{
		driver.quit();
		
	}
	
	@Test (priority = 3)
	public void Verify_loginPageURL()
	{
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}
	
	@Test(enabled = false)
	public void Verify_LoginWith_ValidCredentials_Simpleway()
	{
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("tony1@gipsy.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
	}
	
	@Test (priority = 2)
	public void Verify_LoginWith_ValidCredentials_WithConfigFile()
	{
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(conProp.getProperty("validUsername"));
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(conProp.getProperty("validpassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
	}
	
	@Test (priority=3, dataProvider = "testvaliddataProvider1")
	public void Verify_LoginWith_ValidCredentials_WithTestDataProvider(String email, String password)
	{
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://tutorialsninja.com/demo/");
		
		LoginPageElements login_Page_ElementsObj = new LoginPageElements(driver);
//		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		login_Page_ElementsObj.click_MyAccount();
//		driver.findElement(By.linkText("Login")).click();
		login_Page_ElementsObj.click_login_Text();
//		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);
//		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(password);
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		login_Page_ElementsObj.enter_email(email);
		login_Page_ElementsObj.enter_password(password);
		login_Page_ElementsObj.click_on_LoginInButton();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
	}
	
	@Test (priority = 1)
	public void Verify_LoginWith_InValidEmail()
	{
		LoginPageElements login_Page_ElementsObj = new LoginPageElements(driver);
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://tutorialsninja.com/demo/");
//		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		login_Page_ElementsObj.click_MyAccount();
//		driver.findElement(By.linkText("Login")).click();
		login_Page_ElementsObj.click_login_Text();
//		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("tony1"+generateEmailwithTimeStamp()+"@gipsy.com");
//		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("tony1"+Utilities.generateEmailwithTimeStamp()+"@gipsy.com");  //Imported from Utilities class
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateEmailwithTimeStamp());//Imported from Utilities class
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
//		String actualWarningMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String actualWarningMessage = login_Page_ElementsObj.get_Actualwarningmessage();
//		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		String expectedWarningMessage = login_Page_ElementsObj.expectedWarning_Message();
//		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Warning: No match for E-Mail Address')]")).isDisplayed());
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Error message is wrong" );
		
	}
	
//	public String generateEmailwithTimeStamp()
//	{
//		Date date = new Date();
//		return date.toString().replace(" ", "_").replace(":", "_"); 
//	}
}
