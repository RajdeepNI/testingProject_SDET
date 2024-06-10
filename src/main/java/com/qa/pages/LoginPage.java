package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.testbase.TestBase;

public class LoginPage extends TestBase {
	
	//PageFactory  - Object Repository
	/*Locators*/
	@FindBy(linkText = "Login")public WebElement loginBtn;
	
	
	
	/*Automation Script*/
	public LoginPage() {
		// page factory initiation
		PageFactory.initElements(driver,this);
	}
	
	
	//Actions
	public String validatePageTitle() {
		return driver.getTitle();
	}
	
	public void validatePageLogin() {
		
	}
	
	public void checkLoginDetails() {
		
	}
	
}
