package com.qa.pages;


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
//	@FindBy(xpath="//*[@id=\'b2indexPage\']/div[40]/div/div/div/div[2]/div/a/span") public WebElement frontPopUp;
//	@FindBy(xpath="//*[@id=\'b2indexPage\']/div[40]/div/div/div/div[1]/div[1]/div/button")public WebElement frontPopUpCross;
	@FindBy(xpath="//nav[@class='Header_bar']/child::div[2]/child::a[2]/span[text()='Register']")public WebElement registration;
	@FindBy(xpath="//nav[@class='Header_bar']/child::div[2]/child::div/a/span[contains(text(),'Sign in')]")public WebElement signIn;
	@FindBy(xpath = "//div[@class='page-header']//child::h1[text()='Sign in or create an account']")public WebElement signInPage;
	@FindBy(id="username") public WebElement emailTxtBox;
	@FindBy(xpath = "//form[@class='nw-signin']//child::div[2]//child::div[2]//span[text()='Continue with email']")public WebElement loginBtn;
	@FindBy(xpath="//div[@id='root']//child::div[@class='page-header']/h1[text()='Enter your password']")public WebElement pwdPage;
	@FindBy(id="password")public WebElement pwdTxtBox;
	@FindBy(xpath = "//form[@class='nw-signin']/div/div[2]/div")public WebElement signIN;
	@FindBy(linkText = "Press and Hold") public WebElement pressHold;
	

	/*Automation Script*/
	public LoginPage() {
		// page factory initiation
		PageFactory.initElements(driver,this);

	}


	//Actions
	public String validatePageTitle() {
		return driver.getTitle();
	}

	/*public void validatePageSignUp() throws InterruptedException {
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
	 */
	public void validateLoginDetails() {
		try {
			driver.navigate().refresh();
			Thread.sleep(1000);
			
			
			signIn.click();
			Thread.sleep(1000);
			if(signInPage.isDisplayed()) {
				Thread.sleep(1000);
				String mail = TestUtil.MAIL;
				emailTxtBox.sendKeys(mail);
				Thread.sleep(1000);
				loginBtn.click();
				Thread.sleep(1000);
				System.out.println(driver.getTitle());
				if(pwdPage.isDisplayed()) {
					Thread.sleep(1000);
					pwdTxtBox.sendKeys(TestUtil.PWD);
					Thread.sleep(1000);
					Actions act = new Actions(driver);
					act.clickAndHold(pressHold).build().perform();
					signIN.click();
				}
				else System.out.println("Sorry | Email id not registered !!");
				
			}
			else System.out.println("Sorry | Not able to enter the login page !!");
			Thread.sleep(1000);
			String currURL = driver.getCurrentUrl();
			System.out.println(currURL);
			Thread.sleep(8000);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
