package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.LoginPage;
import com.qa.testbase.TestBase;

public class LoginPageTest extends TestBase {
	
	
	//Author Rajdeep Gupta
	
	
	LoginPage loginPage;
	
	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
			
	}
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.validatePageTitle();
		Assert.assertEquals(title,"Stock Screener and fundamental analysis tool for Indian stocks - Screener");
	}
	
	@Test(priority = 2)
	public void SignUpPageTest() throws InterruptedException {
		loginPage.validatePageSignUp();
	}
	
	@Test(priority = 3)
	public void LoginpageTest() {
		loginPage.validateLoginDetails();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
