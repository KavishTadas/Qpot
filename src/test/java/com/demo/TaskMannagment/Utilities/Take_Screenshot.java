package com.demo.TaskMannagment.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.demo.TaskMannagment.TestCases.Loop_Base;




//Define a class named Take_Screenshot that extends BaseClass
public class Take_Screenshot extends Loop_Base{

	// Declare static variables to store destination, image name, and screenshot path
	public static String imageName;
	public static String screenshotPath;

	// Method for capturing a screenshot with a given filename
	public static String captureScreenshot(String filename) throws IOException {
		
		// Create a date format to append to the image name for uniqueness
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
		
		// Format the current date and time and assign it to imageName
		imageName = dateFormat.format(new Date());

		// Define the input folder based on the provided filename
		String inputFolder = filename;
		// Construct the relative path for the screenshot within the "/Report/" directory
		String relativePath = "/Report//Report-" + Report_Sub_Folder + "//" + inputFolder + " - " + imageName + ".png";
		// Set the absolute path for the screenshot using the system property "user.dir"
		screenshotPath = System.getProperty("user.dir") + relativePath;
		// Print the generated screenshot path to the console
		System.out.println("Screenshot Path: " + screenshotPath);

		// Create a TakesScreenshot instance from the WebDriver (assuming 'driver' is defined somewhere)
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);															// Capture the screenshot as a File

		// Create a File instance representing the destination file with the specified path
		File destFile = new File(screenshotPath);

		// Copy the captured screenshot file to the specified destination
		FileUtils.copyFile(srcFile, destFile);

		// Return the input folder (filename) after capturing and saving the screenshot
		return screenshotPath;
	}
}
