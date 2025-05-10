package com.lms.qa.pagesWebelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageWebelements
{
	public WebDriver driver;
	public HomePageWebelements(WebDriver driver)
	{
		this.driver = driver;
//		PageFactory.initElements(driver, HomePageWebelements.class); // We can write in this way Or below
		PageFactory.initElements(driver, this);
	}
	
	// Objects
	@FindBy(xpath = "//h1[text()='Student home']")
	private WebElement studentHomeLogo;
	
	@FindBy(xpath = "//span[@data-testid='past_classes']")
	private WebElement pastClassestext;
	
	@FindBy(xpath = "//span[contains(@data-testid,'current_classes')]")
	private WebElement currentClassestext;
	
	@FindBy(xpath = "//h1[normalize-space()='Student home']")
	private WebElement studentHometext;
	// Actions
	public boolean studentHomeLogo_isDisplayed()
	{
		return studentHomeLogo.isDisplayed();
	}
	
	public void currentClassVisiblity()
	{
		currentClassestext.isDisplayed();
	}
	
	public void click_pastClass()
	{
		pastClassestext.click();
	}
	
	public void verifyStudentHomeText()
	{
		studentHometext.isDisplayed();
	}
	
	public WebElement getPastclassText()
	{
		return pastClassestext;
	}
	
		
}
