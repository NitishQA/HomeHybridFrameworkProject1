package com.lms.qa.pagesWebelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPageWebelements 
{
	public WebDriver driver;
	
	// Objects
		@FindBy(xpath = "//input[@placeholder='Search']")
		private WebElement searchbar;
		
		@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
		private WebElement searchbutton;
		
		@FindBy(xpath = "(//p[contains(text(),\"Canon's press material for the EOS 5D states that \")])[1]")
		private WebElement actual_ValidSearchResultText;
		
		@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criter')]")
		private WebElement actualInvalidSearchText;
		
		
		// Actions
		public void enterValidSearch(String Validproductname)
		{
			searchbar.sendKeys(Validproductname);
		}

		public void enterInValidSearch(String InValidproductname)
		{
			searchbar.sendKeys(InValidproductname);
		}
		
		public void click_on_searchbutton()
		{
			searchbutton.click();
		}
		
		public String actual_ValidSearchText()
		{
			return actual_ValidSearchResultText.getText();
		}
		
		public String expected_ValidSearchText()
		{
			String validSearchMessage = "Canon's press material for the EOS 5D states that it 'defines (a) new D-SLR category', while we're n..";
			return validSearchMessage;
		}
		
		public String expectedinvalidSearchText()
		{
			String invalidSearchMessage = "There is no product that matches the search criteria.";
			return invalidSearchMessage;
		}
		
		public String actualinvalidProductText()
		{
			return actualInvalidSearchText.getText();
		}
		

	
	public LandingPageWebelements(WebDriver driver)
	{
		this.driver = driver;
		
//		In order to initialize the all @FindBy webelements, to overcome stale_Element_Reference exception we need to write below line of pageFactory
//		PageFactory.initElements(driver, LoginPageElements.class); // We can write in this way Or below
		PageFactory.initElements(driver, this);
	}
	

	

}
