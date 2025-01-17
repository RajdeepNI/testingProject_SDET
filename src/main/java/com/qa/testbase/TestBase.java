package com.qa.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

	
	//Reading data from excel sheet
	public static String readFromExcel(String path,String sheetName,int rowNo,String ColName) throws IOException {
		String res=null;
		int colIdx = Integer.MAX_VALUE;
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet(sheetName);
		XSSFRow row = sh.getRow(0);
		int lastCol = row.getLastCellNum();
		for(int i=0;i<lastCol;i++) {
			if(row.getCell(i).getStringCellValue().equalsIgnoreCase(ColName)) {
				colIdx = i;
				break;
			}
			else continue;
		}
		XSSFRow row1 = sh.getRow(rowNo);
		XSSFCell col1 = row1.getCell(colIdx);
		if(col1 != null) {
			switch(col1.getCellType()) {
			case STRING:
				res = col1.getStringCellValue();
				break;
			case NUMERIC:
				if(DateUtil.isCellDateFormatted(col1)) {
					res = col1.getDateCellValue().toString();
				}
				else {
					res = Integer.toString((int)col1.getNumericCellValue());
				}
				break;
			case BOOLEAN:
				res = Boolean.toString(col1.getBooleanCellValue());
				break;
			case FORMULA:
				res = col1.getCellFormula();
				break;
			case BLANK:
				res = null;
				break;
			default:
				res = "Unknown cell type";
			}
		}
		wb.close();
		fis.close();
		return res;
	}

	
	public static String pwdGenerator(int length) {
		final String CHARACTORS="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890#!@$%^&*()_+=<>[]{}";
		SecureRandom random = new SecureRandom();
		StringBuilder strB = new StringBuilder();
		int count=0;
		while(count<length) {
			int idx = random.nextInt(CHARACTORS.length());
			strB.append(CHARACTORS.charAt(idx));
		}
		return strB.toString();
	}
	
	public static String userNmGenerator() {
		final String[] NAME = {"Admin","RAJDEEP100","PARA21","9PARA_SF","7SF_PARA",""};
		SecureRandom randomName = new SecureRandom();
		int idx = randomName.nextInt(NAME.length);
		return NAME[idx];
	}
	
	public static void writeOnExcel(String path,String sheetNm,int r,String col,String output) throws IOException {
		int colIdx = Integer.MAX_VALUE;
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet(sheetNm);
		XSSFRow row = sh.getRow(0);
		int lastCol = row.getLastCellNum();
		for(int i=0;i<lastCol;i++) {
			if(row.getCell(i).getStringCellValue().equalsIgnoreCase(col)) {
				colIdx = i;
				break;
			}
			else continue;
		}
		XSSFRow r1 = sh.createRow(r);
		r1.createCell(colIdx).setCellValue(output);
		if(r1.getCell(colIdx).equals(output)) {
			System.out.println("Successfully written !!");
		}
		else System.out.println("Error !!");
		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();		
	}
}
