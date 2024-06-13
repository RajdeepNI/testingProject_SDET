package com.qa.pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.testbase.TestBase;

public class LoginPage extends TestBase {
	//Author Rajdeep Gupta
	

	//PageFactory  - Object Repository
	/*Locators*/
	@FindBy(xpath="//div[@class='flex flex-gap-8']/a[2]")public WebElement signUp;
	@FindBy(xpath="//div[@class='flex flex-gap-8']/a[1]")public WebElement login;
	
	@FindBy(xpath = "//div[@class='flex flex-column flex-space-between']/div/h1")public WebElement signUpHeading;
	@FindBy(xpath = "//div[@class='form-field'][1]/input")public WebElement mailTxtBox;
	@FindBy(xpath = "//div[@class='form-field'][2]/input")public WebElement remailTxtBox;
	@FindBy(xpath = "//div[@class='form-field'][3]/input")public WebElement pwd;
	@FindBy(xpath="/html/body/main/div[2]/div[2]/form/button")public WebElement signUpBtn;
	
	@FindBy(xpath="//div[@class='flex flex-column flex-space-between']/div/h1")public WebElement loginMSG;
	@FindBy(xpath = "//div[@class='form-field'][1]//input")public WebElement emailTxtBox;
	@FindBy(xpath="//div[@class='form-field'][2]//input")public WebElement loginPWD;
	@FindBy(xpath ="//button[@class='button-primary']")public WebElement loginBtn;
	
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
		try {
			signUp.click();
			Thread.sleep(1000);
			if(signUpHeading.isDisplayed()) {
				//String mail = EMAIL+"@"+DOMAIN;
				String mail = emailIdGenerator();
				mailTxtBox.sendKeys(mail);
				remailTxtBox.sendKeys(mail);
			    pwd.sendKeys(PASSWORD);
			    signUpBtn.click();
			    Thread.sleep(2000);
			}
			else System.out.println("User is on wrong page");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void validateLoginDetails() {
		try {
			login.click();
			Thread.sleep(1000);
			System.out.println(loginMSG.toString());
			String mail = EMAIL+"@"+DOMAIN;
			System.out.println(mail);
			emailTxtBox.sendKeys(mail);
			loginPWD.sendKeys(PASSWORD);
			Thread.sleep(1000);
			loginBtn.click();
			Thread.sleep(1000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
