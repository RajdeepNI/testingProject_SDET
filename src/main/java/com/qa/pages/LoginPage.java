package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.testbase.TestBase;

public class LoginPage extends TestBase {
	
	//PageFactory  - Object Repository
	/*Locators*/
	@FindBy(linkText = "Login")public WebElement loginBtn;
	@FindBy(xpath="//a[@class='YLCOuy']//picture//img")public WebElement fKartLogo;
	@FindBy(xpath = "//ul[@class='_3YjYK7 ecs1XG']//a[1]//span[2]")public WebElement signUp;
	@FindBy(xpath = "//div[@class='ZJ3AS1']//a")public WebElement createAC;
	
	
	
	
	/*Automation Script*/
	public LoginPage() {
		// page factory initiation
		PageFactory.initElements(driver,this);
	}
	
	
	//Actions
	public String validatePageTitle() {
		return driver.getTitle();
	}
	
	public void validatePageSignUp() throws InterruptedException {
		if(fKartLogo.isDisplayed()) {
			loginBtn.click();
			createAC.click();
			Thread.sleep(1000);
			
			
		}
	}
	
	public void validatePageLogin() {
		
	}
	
	
}
