package com.lms.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities 
{
	
	public static String generateEmailwithTimeStamp()
	{
		Date date = new Date();
		return "tony1"+date.toString().replace(" ", "_").replace(":", "_")+"@gipsy.com"; 
	}

	public static Object[][] getTestdatafromExcel(String sheetname)
	{
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\lms\\qa\\testdata\\tutninjadatafile.xlsx");
		XSSFWorkbook workbook = null;
		try 
		{
		FileInputStream fisExcel = new FileInputStream(excelFile);
		
        workbook = new XSSFWorkbook(fisExcel);
		}
        catch(Throwable e)
		{
        	e.printStackTrace();
		}
        
		XSSFSheet sheet = workbook.getSheet(sheetname);
		
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][columns];
		
		for(int i=0; i<rows; i++)
		{
			XSSFRow rowval = sheet.getRow(i+1);
			
			for(int j=0; j<columns; j++)
			{
				XSSFCell cellval = rowval.getCell(j);
				CellType celltype = cellval.getCellType();
				
				
				switch (celltype) 
				{
				case STRING:
					data[i][j] = cellval.getStringCellValue();
					break;

				case NUMERIC:
					data[i][j] = Integer.toString((int)cellval.getNumericCellValue());
					break;
					
				case BOOLEAN:
					data[i][j] = cellval.getBooleanCellValue();
					break;
				}
			}
		}
		
		return data;
		
	}
	
	public static String captureScreenshot (WebDriver driver, String testName)
	{
		File scrfScreenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\ScreenShotsFolder\\"+testName+".png";
		try 
		{
			FileHandler.copy(scrfScreenshotFile, new File(destinationScreenshotPath));
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
		return destinationScreenshotPath;
	}
}
