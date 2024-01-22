package com.kavi.demo.TestCases;

import java.lang.reflect.Method;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.kavi.demo.PageObjects.Login_PO;
import com.kavi.demo.Utilities.ExcelReadWrite;
import com.kavi.demo.Utilities.Take_Screenshot;

public class Login_TC extends BaseClass {
	public static Take_Screenshot takeScreenshot = new Take_Screenshot();

	public int i;

	@Test
	public void LoginTest(Method M) throws Exception {
		logger.info("Commencing execution of Test Cases.");
		
		for (i = 1; i <= 20; i++) {

			String filepath = "E:\\Data_Driven_Demo\\Data.xlsx";
			String Sheet = "Data1";
			ExcelReadWrite excel = new ExcelReadWrite(filepath, Sheet);
			Thread.sleep(4000);

			String message = null;

			Login_PO pob = new Login_PO(driver);

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

			Thread.sleep(2000);
			if (isAlertPresent(driver)) {
				Alert alert = driver.switchTo().alert();
				message = alert.getText();
				alert.accept();
			}

			if (driver.getCurrentUrl().equalsIgnoreCase("https://qpot.omfysgroup.com/dashboard")) {
				Thread.sleep(2000);
				pob.clickiconButton();
				Thread.sleep(2000);
				pob.clicklogoutButton();
				excel.writeData(i, 2, "Pass");
				ExtentTest.info("Login Successful.");
			} else {
				excel.writeData(i, 2, "Fail");
				ExtentTest.info("Login Failed.");
				excel.writeData(i, 3, message);
				ExtentTest.info("Faliure Caused By :- " + message);
				takeScreenshot.captureScreenshot(M.getName());
				String ScreenshotPath = takeScreenshot.screenshotPath;
				System.out.println("ScreenshotPath - " + ScreenshotPath);
				ExtentTest.addScreenCaptureFromPath("./Report - " + takeScreenshot.imageName + ".png")	;
				Thread.sleep(2000);
				pob.clear();

			}
			excel.closeWorkbook();
		}
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
