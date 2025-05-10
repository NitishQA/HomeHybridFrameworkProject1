package com.lms.qa.testcases;

import java.util.Date;

public class DateTimeGenerateExperiment 
{

	public static void main(String[] args) 
	{
		Date date = new Date();
		System.out.println(date.toString().replace(" ", "_").replace(":", "_")); 
	}

}
