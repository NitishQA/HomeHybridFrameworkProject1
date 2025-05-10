package com.lms.qa.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/** For running listeners, under src/test/resources/testng.xlm file - https://prnt.sc/Je-9Li4qPDaJ , 

  <listeners>
		<listener class-name="com.lms.qa.listeners.MyListeners1"></listener>
	</listeners>
	
	
  
 **/

public class MyListeners1 implements ITestListener
{
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Execution of Project TestCases started");
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getName();
		System.out.println(testName+" : Started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getName();
		System.out.println(testName+" : got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getName();
		System.out.println(testName+" : got failed");
		System.out.println("Exception details : "+result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getName();
		System.out.println(testName+" : got Skipped");
		System.out.println("Exception details : "+result.getThrowable());
	}


	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		System.out.println("Execution of Project TestCases Finished");
	}

	
	
}
