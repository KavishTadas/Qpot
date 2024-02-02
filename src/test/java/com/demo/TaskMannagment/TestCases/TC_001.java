package com.demo.TaskMannagment.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.TaskManagement.PageObjects.PageObjectPMO;

public class TC_001 extends Loop_Base{
	
	@Test
	public void Test1() throws Exception {
		
		TC_000 obj = new TC_000();
		obj.loginTest();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@class,'waves-effect') and contains(@class,'waves-block')])[4]")));
		
		PageObjectPMO obj1 = new PageObjectPMO(driver);
		
		obj1.clickProjectManagementMenu();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.omfysgroup.com/project-managementQpotAPi/");
	}
}
