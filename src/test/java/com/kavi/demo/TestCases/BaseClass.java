package com.kavi.demo.TestCases;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.kavi.demo.Utilities.ReadConfig;
import com.kavi.demo.Utilities.Send_Mail_with_Report;
import com.kavi.demo.Utilities.Zip_Folder;

public class BaseClass {
	// Webdriver driver
	public static WebDriver driver;
	public static WebDriverWait wait;

	ReadConfig RC = new ReadConfig(); // Read Properties File
	public String URL = RC.readUrl();

	protected static final Logger logger = LogManager.getLogger("BaseClass_1");

	public static ExtentReports ExtentReports; // TO create report
	public static String Report_Sub_Folder;
	public static com.aventstack.extentreports.ExtentTest ExtentTest;
	public String trimmedString;

	@BeforeSuite
	public void initializeExtentReprts() {

		logger.info("Initializing Extent Reports with the specified configuration");

		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm.ss"); // String
																							// formattedDate=myDateObj.format(myFormatObj);
		Report_Sub_Folder = myDateObj.format(myFormatObj);

		ExtentReports = new ExtentReports();
		ExtentSparkReporter SparkReporter_Alltest = new ExtentSparkReporter(
				"Report\\Report-" + Report_Sub_Folder + "\\Data_Driven_Demo_Report.html");
		ExtentReports.attachReporter(SparkReporter_Alltest);
		SparkReporter_Alltest.config().setTheme(Theme.DARK);
		// to print the specific information in Report
		ExtentReports.setSystemInfo("Java_Version", System.getProperty("java.version"));
		ExtentReports.setSystemInfo("OS", System.getProperty("os.name"));

	}

	@Parameters("browser")
	@BeforeTest
	public void setUp(String br) throws Exception {

		logger.info("Setting up the WebDriver based on the Chrome browser");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\Data_Driven_Demo\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
			Thread.sleep(5000);

		}
		driver.get(URL);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void initializeReport(ITestContext context) {

		ExtentTest = ExtentReports.createTest(context.getName());
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();

		String Device = capabilities.getBrowserName() + " " + capabilities.getBrowserVersion();
		ExtentTest.assignDevice(Device);
		String Author = context.getCurrentXmlTest().getParameter("Author");
		ExtentTest.assignAuthor(Author);
	}

//	@AfterMethod
//	public void alignScreenshotAndReport(Method M) {
//		String ScreenshotPath = ScreenCapture.screenshotPath;
//		System.out.println("ScreenShotPath - " + ScreenshotPath);
//		System.out.println("ScreenShotPath ========================= " + result.getMethod().getMethodName());
//		ExtentTest.addScreenCaptureFromPath("./Report - " + ts.image + ".png")	;
//	}
//	(M.getName() + "//")
	@AfterTest
	public void Demolish() {
		logger.info("Closing the WebDriver after the test");

		driver.close();

		logger.info(
				"-------------------------------------------------------------------------------------------------------------------------------");
	}

	@AfterSuite
	public void generateExtentReprts() {

		logger.info("Flushing the Extent Reports to generate the report" + "\n------------------------------------");
		ExtentReports.flush();

		Zip_Folder.Zp();

		Send_Mail_with_Report.Mail();

	}

}
