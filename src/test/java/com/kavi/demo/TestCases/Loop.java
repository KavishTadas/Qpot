package com.kavi.demo.TestCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.kavi.demo.PageObjects.Login_PO;
import com.kavi.demo.Utilities.ExcelReadWrite;

public class Loop extends Loop_Base {
	 
	public  int i = 1;
	
    @Test(invocationCount = 20)
	public void Repeat() throws Exception {
		
    	String filepath = "E:\\Data_Driven_Demo\\Data.xlsx";
		String Sheet = "Data1";
		ExcelReadWrite excel = new ExcelReadWrite(filepath, Sheet);
		Thread.sleep(4000);
    	
		String message = null;

		Login_PO pob = new Login_PO(driver);
		
		pob.clear();

		Thread.sleep(2000);
		pob.enterusername(excel.getData(i, 0));
		System.out.println("Username - " + excel.getData(i, 0));
		ExtentTest.info("Username - " + excel.getData(i, 0));
		Thread.sleep(2000);
		pob.enterPassword(excel.getData(i, 1));
		System.out.println("Password - " + excel.getData(i, 1));
		ExtentTest.info("Password - " + excel.getData(i, 1));
		Thread.sleep(2000);
		pob.clickLoginButton();
		
		Thread.sleep(5000);
		if (isAlertPresent(driver)) {
			Alert alert = driver.switchTo().alert();
			message = alert.getText();
			alert.accept();
			ExtentTest.info("Alert Msg - " + message);
		}
		
		i++;
		Thread.sleep(2000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://qpot.omfysgroup.com/dashboard");
		
		Thread.sleep(2000);
		pob.clickiconButton();
		Thread.sleep(2000);
		pob.clicklogoutButton();
		excel.writeData(i, 2, "Pass");
		
 	}
    
    private static boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
