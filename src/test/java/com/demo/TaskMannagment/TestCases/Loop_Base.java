package com.demo.TaskMannagment.TestCases;

import java.io.IOException;
import java.lang.reflect.Method;
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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.demo.TaskMannagment.Utilities.ReadConfig;
import com.demo.TaskMannagment.Utilities.Send_Mail_with_Report;
import com.demo.TaskMannagment.Utilities.Take_Screenshot;
import com.demo.TaskMannagment.Utilities.Zip_Folder;


public class Loop_Base {

	// Webdriver driver
	public static WebDriver driver;
	public static WebDriverWait wait;

	ReadConfig RC = new ReadConfig(); // Read Properties File
	public String PM_URL = RC.readPM_Url();
	public String PM_ID = RC.readPM_Username();
	public String PM_PWD = RC.readPM_Password();
	public static Take_Screenshot screenCapture;

//	public static String filepath = "E:\\Data_Driven_Demo\\Data.xlsx";
//	public static String Sheet = "Data1";
//	public static ExcelReadWrite excel = new ExcelReadWrite(filepath, Sheet);
//	public static final int rowCount = excel.getRowCount();

	protected static final Logger logger = LogManager.getLogger("BaseClass_1");

	public static ExtentReports ExtentReports; // TO create report
	public static String Report_Sub_Folder;
	public static com.aventstack.extentreports.ExtentTest ExtentTest;

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
		driver.get(PM_URL);
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

	@AfterMethod
	public void After_TC_Failure(Method M, ITestResult result) throws IOException {
		screenCapture = new Take_Screenshot();

		if (result.getStatus() == ITestResult.FAILURE) {
			String ScreenshotPath = screenCapture
					.captureScreenshot(result.getTestContext().getName() + " - " + result.getMethod().getMethodName());

			ExtentTest.addScreenCaptureFromPath(ScreenshotPath);
			Throwable Error_msg_of_Console = result.getThrowable();

			System.out.println("Error_msg_of_Console : " + Error_msg_of_Console);
			ExtentTest.fail(Error_msg_of_Console);

//			excel.writeData(l.i, 2, "Pass");

			logger.warn("Warning: Method Failed: " + result.getMethod().getMethodName());
			// logger.error("Error found: " + Error_msg_of_Console);
		} else if (result.getStatus() == ITestResult.SKIP) {
			System.out.println("Test Skipped: " + result.getMethod().getMethodName());
			ExtentTest.skip("Test Skipped: " + result.getMethod().getMethodName());

			logger.info("Test Skipped: " + result.getMethod().getMethodName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			ExtentTest.pass(M.getName() + " method is Passed");

			logger.info("Test Case Method: " + result.getMethod().getMethodName() + " is Passed");
		}

		// ExtentTest.assignCategory(M.getAnnotation(Test.class).groups());

	}

	@AfterTest
	public void Demolish() {
		logger.info("Closing the WebDriver after the test");

		driver.close();
	}

	@AfterSuite
	public void generateExtentReprts() {

		logger.info("Flushing the Extent Reports to generate the report"
				+ "\n------------------------------------------------------------------------------------------------------------------------------------");
		ExtentReports.flush();

		Zip_Folder.Zp();

		Send_Mail_with_Report.Mail();

	}
}
