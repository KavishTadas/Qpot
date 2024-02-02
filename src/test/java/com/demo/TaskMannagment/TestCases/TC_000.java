package com.demo.TaskMannagment.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.TaskManagement.PageObjects.PageObjectPMO;

public class TC_000 extends Loop_Base {

    @Test
    public void loginTest() throws Exception {
    	
        PageObjectPMO obj = new PageObjectPMO(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emp_code")));
        System.out.println(PM_ID);
        obj.setUsername(PM_ID);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        obj.setPassword(PM_PWD);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class=\"submit\"]")));
        obj.clickLogin();

        // Wait for the dashboard link to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@class,'waves-effect') and contains(@class,'waves-block')])[4]")));


        // Assert the current URL after login
        String expectedDashboardUrl = "https://demo.omfysgroup.com/project-managementQpotAPi/dashboard";
        Assert.assertEquals(driver.getCurrentUrl(), expectedDashboardUrl, "Unexpected URL after login");
    }
}
