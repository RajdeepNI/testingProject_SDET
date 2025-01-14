package com.qa.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.utils.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	//Author Rajdeep Gupta

	public static WebDriver driver;
	public static Properties prop;
	
	
	public static void scrollDown(WebElement targetElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
	}
	
	public static void takeScreenShot(WebDriver driver,String targetPath) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File(targetPath);
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}
	
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream file = new FileInputStream("C:/Users/Acer/Desktop/SDET_Git/test/src/main"
					+ "/java/com/qa/config/config.properties");
			prop.load(file);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("Chrome")) {
			driver=WebDriverManager.chromedriver().create();   // using WebDriverManager
		}
		else if(browserName.equalsIgnoreCase("Filefox")) {
			driver=WebDriverManager.firefoxdriver().create();
		}
		else{
			driver=WebDriverManager.edgedriver().create();
		}
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		
		String url = prop.getProperty("Url");
		driver.get(url);
	}
	

	
	
}
