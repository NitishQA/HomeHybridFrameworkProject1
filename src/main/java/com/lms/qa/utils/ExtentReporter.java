package com.lms.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter 
{
	public static ExtentReports generateExtentReport()
	{
		ExtentReports extentReportsObj = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		
		ExtentSparkReporter extentSparkReporterObj = new ExtentSparkReporter(extentReportFile);
		
		extentSparkReporterObj.config().setTheme(Theme.DARK);
		extentSparkReporterObj.config().setReportName("LMS Automation Report name");
		extentSparkReporterObj.config().setDocumentTitle("LMS Automation Report title");
		extentSparkReporterObj.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");
		
		extentReportsObj.attachReporter(extentSparkReporterObj);
		
		Properties conFigProp = new Properties();
		
		File conFigPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\lms\\qa\\config\\config.properties");
//		FileInputStream configPropFIS = new FileInputStream(conFigPropFile); Check SS, mostly use Try catch https://prnt.sc/qBhSiCv1akAs
		
		try {
		FileInputStream configPropFIS = new FileInputStream(conFigPropFile);
		conFigProp.load(configPropFIS);
		}
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
		
		
		extentReportsObj.setSystemInfo("Applicaton URL", conFigProp.getProperty("url"));
		extentReportsObj.setSystemInfo("Browser Name", conFigProp.getProperty("browserName"));
		extentReportsObj.setSystemInfo("Operation System", System.getProperty("os.name"));
		extentReportsObj.setSystemInfo("System Username", System.getProperty("user.name"));
		extentReportsObj.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReportsObj;
		
	}
}
