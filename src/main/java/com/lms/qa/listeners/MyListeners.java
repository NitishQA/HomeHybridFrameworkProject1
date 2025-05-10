package com.lms.qa.listeners;
import java.awt.Desktop;
//<class name="com.lms.qa.testcases.LoginPage"/>
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.lms.qa.utils.ExtentReporter;
import com.lms.qa.utils.Utilities;

public class MyListeners implements ITestListener
{
	ExtentReports extentReportsObj;
	ExtentTest extentTestObj;
	String testName;
	@Override
	public void onStart(ITestContext context) 
	{
//		System.out.println("Execution of Test Cases Started");
//		ExtentReports extentReportsObj = ExtentReporter.generateExtentReport(); Make it Global
		extentReportsObj = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		testName = result.getName(); //Make it Global
//		System.out.println(testName+" got successfully started");
//		ExtentTest extentTestObj = extentReportsObj.createTest(testName); // Make it Global
		extentTestObj = extentReportsObj.createTest(testName);
		extentTestObj.log(Status.INFO, testName+": got successfully started");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		testName = result.getName();
//		System.out.println(testName+" got succeed");
		extentTestObj.log(Status.PASS, testName+": got succeed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		testName = result.getName();
//		System.out.println(testName+" got failed");
//		System.out.println("Throawble are : "+result.getThrowable());
		
		WebDriver driver = null;
		try {
		driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
//		File scrfScreenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		String destinationScreenshotPath = System.getProperty("user.dir")+"\\ScreenShotsFolder\\"+testName+".png";
//		try {
//			FileHandler.copy(scrfScreenshotFile, new File(destinationScreenshotPath));
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
		
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, testName);
		
		extentTestObj.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTestObj.log(Status.INFO, result.getThrowable());
		extentTestObj.log(Status.FAIL, testName+" got failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		testName = result.getName();
//		System.out.println(testName+" got skipped");
//		System.out.println("Throwable are : "+result.getThrowable());
		extentTestObj.log(Status.INFO, result.getThrowable());
		extentTestObj.log(Status.SKIP, testName+" got skipped");
		
	}


	@Override
	public void onFinish(ITestContext context) 
	{
		
//		System.out.println("Finished test cases");
		extentReportsObj.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
