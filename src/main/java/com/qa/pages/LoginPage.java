package com.qa.pages;


import org.apache.commons.math3.stat.inference.TestUtils;
import org.openqa.selenium.WebElement;
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
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6")public WebElement dashBoard;
	@FindBy(xpath= "//div[@class='orangehrm-login-forgot']/p[text()=\"Forgot Your Password? \"]")public WebElement forgetPwd;
	@FindBy(xpath="//form[@class='oxd-form']/h6[text()=\"Reset Password\"]")public WebElement ResetPwdHeader;
	@FindBy(xpath="//form[@class='oxd-form']/div[1]/div/div[2]/input[@placeholder='username']")public WebElement ForgetPWDUsername;
	@FindBy(xpath="//form[@class='oxd-form']/div[2]/button[2][text()=\" Reset Password \"]")public WebElement ResetPWDBtn;
	@FindBy(xpath="//div[@class='orangehrm-card-container']/h6[text()=\"Reset Password link sent successfully\"]")public WebElement resetPWDResult;
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[3]/div[2]/p[2]/a")public WebElement EndOfLoginPg;
	
	
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


	public HomePage validateLoginDetails() throws InterruptedException {
		try {
			Thread.sleep(2000);
			if(loginPage.isDisplayed()) {
				System.out.println("Title of the page is "+driver.getTitle());
				Thread.sleep(1000);
				TestBase.scrollDown(EndOfLoginPg);
				Thread.sleep(1000);
				if(forgetPwd.isDisplayed()) {
					forgetPwd.click();
					Thread.sleep(1000);
					if(ResetPwdHeader.isDisplayed()) {
						ForgetPWDUsername.sendKeys(TestUtil.USER_NM);
						Thread.sleep(1000);
						ResetPWDBtn.click();
						Thread.sleep(1000);
						System.out.println(resetPWDResult.getText());
					}
					else System.out.println("Reset Password option is not displayed!!");
				}
				else System.out.println("Forget Password Link is not displayed");
				Thread.sleep(1000);
				driver.get(prop.getProperty("Url"));
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
		return new HomePage(); //return an object of Homepage class
		
	}
	
	
}
