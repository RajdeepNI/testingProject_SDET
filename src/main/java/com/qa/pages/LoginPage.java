package com.qa.pages;


import java.util.Currency;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.testbase.TestBase;
import com.qa.utils.TestUtil;

public class LoginPage extends TestBase {
	//Author Rajdeep Gupta


	//PageFactory  - Object Repository
	/*Locators*/

	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")public WebElement logInBtn;
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/h5")public WebElement loginPage;
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input") public WebElement userName;
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")public WebElement pwd;
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[4]/p")public WebElement forgetPwd;
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6")public WebElement dashBoard;

	//======================================= Script

	/*Automation Script*/

	public LoginPage() {
		// page factory initiation
		PageFactory.initElements(driver,this);
		
	}


	//Actions
	public String validatePageTitle() {
		String title="";
		try {
			Thread.sleep(1000);
			title = driver.getTitle();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return title;
	}


	public void validateLoginDetails() {
		try {
			Thread.sleep(2000);
			if(loginPage.isDisplayed()) {
				System.out.println("Title of the page is "+driver.getTitle());
				Thread.sleep(1000);
				String uName = TestUtil.USER_NM;
				userName.sendKeys(uName);
				Thread.sleep(1000);
				String passWord = TestUtil.PWD;
				pwd.sendKeys(passWord);
				Thread.sleep(1000);
				String loginURL = driver.getCurrentUrl();
				logInBtn.click();
				Thread.sleep(1000);
				String newURL = driver.getCurrentUrl();
				if(!loginURL.equals(newURL) && dashBoard.isDisplayed()) {
					System.out.println("Login Successfull");
					
				}
				else System.out.println("Sorry | Login Unsuccessfull!!");
				Thread.sleep(2000);
			}
			else System.out.println("Sorry | Login page is not displayed!!");

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
