package com.qa.pages;


import java.util.Set;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.testbase.TestBase;

public class LoginPage extends TestBase {
	//Author Rajdeep Gupta
	
	
	//PageFactory  - Object Repository
	/*Locators*/
	@FindBy(xpath="//a[@data-cms-id='ci_3']")public WebElement loginBtn;
	@FindBy(id="email")public WebElement emailTextBox;
	@FindBy(xpath="//*[@id=\"pms-checkbox-doc-mrkt-email-club:adidas:IN:2023710\"]/div/div/label")public WebElement checkBox1;
	@FindBy(xpath="//*[@id=\"pms-checkbox-doc-tnc-memb:adidas:IN:2023112\"]/div/div/label")public WebElement checkBox2;
	@FindBy(xpath="//*[@id=\"two-step-form-button\"]")public WebElement signUpProceed;
	
	
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
			loginBtn.click();
			Thread.sleep(1000);
			String email = emailIdGenerator();
			emailTextBox.sendKeys(email);
			Thread.sleep(1000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			checkBox1.click();
			Thread.sleep(3000);
			checkBox2.click();
			Thread.sleep(3000);
			signUpProceed.click();
			Thread.sleep(2000);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
