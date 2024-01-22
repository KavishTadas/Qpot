package com.kavi.demo.TestCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kavi.demo.PageObjects.Login_PO;

public class DDD extends Loop_Base {

	public static final int invocationCount = rowCount + 1;

	public int i = 1;

	@Test(invocationCount = 20)
	public void DDDT() throws Exception {

		String userName = excel.getData(i, 0);
		String passWord = excel.getData(i, 1);

		String message = null;

		if (userName != null && passWord != null) {

			Login_PO pob = new Login_PO(driver);

			pob.clear();

			Thread.sleep(2000);
			pob.enterusername(userName);
			System.out.println("Username - " + userName);
			ExtentTest.info("Username - " + userName);
			Thread.sleep(2000);
			pob.enterPassword(passWord);
			System.out.println("Password - " + passWord);
			ExtentTest.info("Password - " + passWord);
			Thread.sleep(2000);
			pob.clickLoginButton();

			Thread.sleep(5000);
			if (isAlertPresent(driver)) {
				Alert alert = driver.switchTo().alert();
				message = alert.getText();
				alert.accept();
				ExtentTest.info("Alert Msg - " + message);
				excel.writeData(i, 2, "Fail");
				excel.writeData(i, 3, message);
			}

			int x = i;
			i++;
			Thread.sleep(2000);
			Assert.assertEquals(driver.getCurrentUrl(), "https://qpot.omfysgroup.com/dashboard");

			Thread.sleep(2000);
			pob.clickiconButton();
			Thread.sleep(2000);
			pob.clicklogoutButton();
			excel.writeData(x, 2, "Pass");

		} else if (userName == null && passWord != null) {

			Login_PO pob = new Login_PO(driver);

			pob.clear();

			Thread.sleep(2000);
			ExtentTest.info("Username - " + null);
			Thread.sleep(2000);
			pob.enterPassword(passWord);
			System.out.println("Password - " + passWord);
			ExtentTest.info("Password - " + passWord);
			Thread.sleep(2000);
			pob.clickLoginButton();

			Thread.sleep(5000);
			if (isAlertPresent(driver)) {
				Alert alert = driver.switchTo().alert();
				message = alert.getText();
				alert.accept();
				ExtentTest.info("Alert Msg - " + message);
				excel.writeData(i, 2, "Fail");
				excel.writeData(i, 3, message);
			}
			int x = i;
			i++;
			Thread.sleep(2000);
			Assert.assertEquals(driver.getCurrentUrl(), "https://qpot.omfysgroup.com/dashboard");

			Thread.sleep(2000);
			pob.clickiconButton();
			Thread.sleep(2000);
			pob.clicklogoutButton();
			excel.writeData(x, 2, "Pass");

		} else if (userName != null && passWord == null) {

			Login_PO pob = new Login_PO(driver);

			pob.clear();

			Thread.sleep(2000);
			pob.enterusername(userName);
			System.out.println("Username - " + userName);
			ExtentTest.info("Username - " + userName);
			ExtentTest.info("Password - " + null);
			Thread.sleep(2000);
			pob.clickLoginButton();

			Thread.sleep(5000);
			if (isAlertPresent(driver)) {
				Alert alert = driver.switchTo().alert();
				message = alert.getText();
				alert.accept();
				ExtentTest.info("Alert Msg - " + message);
				excel.writeData(i, 2, "Fail");
				excel.writeData(i, 3, message);
			}

			int x = i;
			i++;
			Thread.sleep(2000);
			Assert.assertEquals(driver.getCurrentUrl(), "https://qpot.omfysgroup.com/dashboard");

			Thread.sleep(2000);
			pob.clickiconButton();
			Thread.sleep(2000);
			pob.clicklogoutButton();
			excel.writeData(x, 2, "Pass");

		} else if (userName == null && passWord == null) {

			Login_PO pob = new Login_PO(driver);

			pob.clear();

			Thread.sleep(2000);
			ExtentTest.info("Username - " + null);
			ExtentTest.info("Password - " + null);
			Thread.sleep(2000);
			pob.clickLoginButton();

			Thread.sleep(5000);
			if (isAlertPresent(driver)) {
				Alert alert = driver.switchTo().alert();
				message = alert.getText();
				alert.accept();
				ExtentTest.info("Alert Msg - " + message);
				excel.writeData(i, 2, "Fail");
				excel.writeData(i, 3, message);
			}

			i++;
			Thread.sleep(2000);
			Assert.assertEquals(driver.getCurrentUrl(), "https://qpot.omfysgroup.com/dashboard");

			throw new SkipException("Skipping test due to a condition");

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
