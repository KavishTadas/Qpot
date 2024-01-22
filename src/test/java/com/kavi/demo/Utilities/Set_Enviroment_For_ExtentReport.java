package com.kavi.demo.Utilities;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Set_Enviroment_For_ExtentReport {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "E:\\Data_Driven_Demo\\Driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		System.out.println(capabilities.getBrowserName());
		System.out.println(capabilities.getBrowserVersion());

		driver.quit();

		System.out.println(System.getProperty("os.name"));
		System.out.println("java_version-" + System.getProperty("java.version"));
	}

}
