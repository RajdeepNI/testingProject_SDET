package com.qa.pages;


import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static org.testng.Assert.*;
import com.qa.testbase.TestBase;
import com.qa.utils.TestUtil;


public class HomePage  extends TestBase {

	//page factories

	@FindBy(xpath = "//header[@class='oxd-topbar']//span[@class='oxd-topbar-header-breadcrumb']/h6[text()=\"Dashboard\"]")public WebElement headerDashboard;
	@FindBy(xpath = "//div[@class='oxd-topbar-header-userarea']//li/span/p")public WebElement userName;
	@FindBy(xpath = "//div[@class='oxd-topbar-header-userarea']//li/ul[@role='menu']/li[1]/a[text()=\"About\"]")public WebElement AboutDB;
	@FindBy(xpath = "//div[@class='oxd-topbar-header-userarea']//li/ul[@role='menu']/li[2]/a[text()=\"Support\"]")public WebElement SupportDB;
	@FindBy(xpath = "//div[@class='oxd-topbar-header-userarea']//li/ul[@role='menu']/li[3]/a[text()=\"Change Password\"]")public WebElement ChangePwdDB;
	@FindBy(xpath = "//div[@class='oxd-topbar-header-userarea']//li/ul[@role='menu']/li[4]/a[text()=\"Logout\"]")public WebElement LogoutDB;
	@FindBy(xpath = "//div[@class='orangehrm-modal-header']/h6[text()=\"About\"]") public WebElement AbountPopup;
	@FindBy(xpath="//div[@class='oxd-grid-2 orangehrm-about']/div[2]/p")public WebElement CompanyName;
	@FindBy(xpath="//div[@class='oxd-grid-2 orangehrm-about']/div[4]/p")public WebElement Version;
	@FindBy(xpath="//div[@class='oxd-grid-2 orangehrm-about']/div[6]/p")public WebElement ActiveEmpNo;
	@FindBy(xpath="//div[@class='oxd-grid-2 orangehrm-about']/div[8]/p")public WebElement TerminatedEmpNo;
	@FindBy(xpath="//div[@id='app']//div[@class='oxd-dialog-container-default--inner']/div/button")public WebElement crossBtn;
	@FindBy(xpath="//div[@id='app']//div[@class='oxd-topbar-body-nav-slot']/button[@title='Help']")public WebElement HelpMark;
	@FindBy(xpath="//div[@class='oxd-form-row']//div[@class='oxd-input-group']/div[2]/p")public WebElement userNm;
	@FindBy(xpath="//div[@class='oxd-form-row']//div[@class='oxd-input-group oxd-input-field-bottom-space']/div[2]/input")public WebElement prevPWD;
	@FindBy(xpath="//form[@class='oxd-form']/div[2]/div/div[1]/div/div[2]/input")public WebElement newPWD_1;
	@FindBy(xpath="//form[@class='oxd-form']/div[2]/div/div[2]/div/div[2]/input")public WebElement newPWD_2;

	//======================================= Script

	/*Automation Script*/

	public HomePage() {
		PageFactory.initElements(driver, this);
	}


	public void ValidateHomepage() throws InterruptedException, IOException {
		System.out.println(driver.getTitle());
		Thread.sleep(1000);
		if(headerDashboard.isDisplayed()) {
			System.out.println("User i on Homepage");
			Scanner sc= new Scanner(System.in);
			String ssNm = sc.next();
			Thread.sleep(1000);
			String targetPath = "C:\\Users\\Acer\\Desktop\\SDET_Git\\test\\Screenshots\\"+ssNm+".png";
			TestBase.takeScreenShot(driver, targetPath);
			sc.close();

		}
		else System.out.println("Error !!");
	}

	public void ValidateHomePageNavBar() throws InterruptedException, IOException {
		System.out.println(userName.getText());
		userName.click();
		Thread.sleep(1000);
		AboutDB.click();
		Thread.sleep(1000);
		if(AbountPopup.isDisplayed()) {
			assertEquals(CompanyName.getText(),"OrangeHRM","Company name not matched!!");
			assertEquals(Version.getText(),"OrangeHRM OS 5.7","Version not matched!!");
			System.out.println("Active Number of Emplpyees - "+ActiveEmpNo.getText());
			System.out.println("Terminated Number of Employees - "+TerminatedEmpNo.getText());
			Thread.sleep(1000);
			crossBtn.click();
		}
		userName.click();
		Thread.sleep(1000);
		SupportDB.click();
		Thread.sleep(1000);
		Assert.assertTrue(HelpMark.isDisplayed());
		userName.click();
		Thread.sleep(1000);
		ChangePwdDB.click();
		//pending
		if(userNm.getText().equals(TestUtil.USER_NM)) {

			String userName = TestBase.userNmGenerator();
			String password = TestBase.pwdGenerator(TestUtil.passwordLength);
			prevPWD.sendKeys(TestUtil.PWD);
			TestBase.writeOnExcel(TestUtil.Data_Homepg,TestUtil.sh_Name,1,"Username", userName);
			Thread.sleep(1000);
			TestBase.writeOnExcel(TestUtil.Data_Homepg,TestUtil.sh_Name,2,"Password", password);
			newPWD_1.sendKeys(password);
			Thread.sleep(1000);
			newPWD_2.sendKeys(password);
			Thread.sleep(1000);
			
		}
		Thread.sleep(1000);


	}

}
