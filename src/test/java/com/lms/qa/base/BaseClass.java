package com.lms.qa.base;
// Test1 Comment 
import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass 
{
	public WebDriver driver;
	public Properties conProp;
	
//	public void loadPropertiesFile() // either create in this way or create constructor public BaseClass()
	public BaseClass()
	{
		conProp = new Properties();
		File conPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\lms\\qa\\config\\config.properties");
		try
		{
		FileInputStream conFis = new FileInputStream(conPropFile);
		conProp.load(conFis);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
	
	public WebDriver intializeBrowserandOpenApplicationURL(String browserName) throws InterruptedException
	{

		if(browserName.equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver(); 
		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			driver = new FirefoxDriver(); 
		}		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			System.out.println("Invalid broswer entry");
		}
		
		
		driver.manage().window().maximize();
		driver.get(conProp.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		Thread.sleep(5000);
		
		return driver;
	}
}
