package com.lms.qa.pagesWebelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageElements 
{
	public WebDriver driver;

//	This is called Page factory design
	
	// Objects
	
	    @FindBy(xpath = "//span[text()='My Account']")
	    private WebElement myAccount;
	    
	    @FindBy(linkText = "Login")
	    private WebElement login_Text;
	
		@FindBy(xpath = "//input[@id='input-email']")
		private WebElement emailField;
		
		@FindBy(xpath = "//input[@id='input-password']")
		private WebElement passwordField;
		
		@FindBy(xpath = "//input[@value='Login']")
		private WebElement loginInButton;
		
		@FindBy(xpath = "//div[contains(@class,'MuiAlert-message')]")
		private WebElement warningMessage;
		
		@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
		private WebElement actualWarningMessage;
		
		
		// Actions
		
		public void click_MyAccount()
		{
			myAccount.click();
		}
		
		public void click_login_Text()
		{
			login_Text.click();
		}
		
		public void enter_email(String usernameText)
		{
			emailField.sendKeys(usernameText);
		}
		
		public void enter_password(String passwordText)
		{
			passwordField.sendKeys(passwordText);
		}
		
		public void click_on_LoginInButton()
		{
			loginInButton.click();
		}
		
		public String get_Actualwarningmessage()
		{
			return actualWarningMessage.getText();
			
		
		}
		
		public String expectedWarning_Message()
		{
			String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
			return expectedWarningMessage;
		}
	
	public LoginPageElements(WebDriver driver)
	{
		this.driver = driver;
		
//		In order to initialize the all @FindBy webelements, to overcome stale_Element_Reference exception we need to write below line of pageFactory
//		PageFactory.initElements(driver, LoginPageElements.class); // We can write in this way Or below
		PageFactory.initElements(driver, this);
	}
	

	

}
