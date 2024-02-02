package com.demo.TaskManagement.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectPMO {

    private final WebDriver driver;

    // Constructor to initialize the WebDriver and PageFactory
    public PageObjectPMO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators for elements
    @CacheLookup
    @FindBy(id = "emp_code")
    private WebElement userNameInput;

    @CacheLookup
    @FindBy(id = "password")
    private WebElement passwordInput;

    @CacheLookup
    @FindBy(xpath = "//button[@class=\"submit\"]")
    private WebElement loginButton;

//    @CacheLookup
//    @FindBy(className = "menu-toggle waves-effect waves-block toggled")
//    private WebElement projectManagementMenu;
//
//    @CacheLookup
//    @FindBy(xpath = "(//a[@class=\"menu-toggle waves-effect waves-block\"])[1]")
//    private WebElement projectsSubMenu;
//
//    @CacheLookup
//    @FindBy(xpath = "(//a[@class=\"menu-toggle waves-effect waves-block\"])[2]")
//    private WebElement approvalSubMenu;
//
//    @CacheLookup
//    @FindBy(xpath = "(//a[@class=\"menu-toggle waves-effect waves-block\"])[3]")
//    private WebElement taskManagementSubMenu;
//
//    @CacheLookup
//    @FindBy(xpath = "(//a[@class=\" waves-effect waves-block\"])[11]")
//    private WebElement projectManagement;
//
//    @CacheLookup
//    @FindBy(xpath = "(//a[@class=\"menu-toggle waves-effect waves-block toggled\"])[4]")
//    private WebElement taskAndTimeSubMenu;

    // Methods to interact with elements

    public void setUsername(String username) {
        userNameInput.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

//    public void clickProjectManagementMenu() {
//        projectManagementMenu.click();
//    }
//
//    public void clickProjectsSubMenu() {
//        projectsSubMenu.click();
//    }
//
//    public void clickApprovalSubMenu() {
//        approvalSubMenu.click();
//    }
//
//    public void clickTaskManagementSubMenu() {
//        taskManagementSubMenu.click();
//    }
//
//    public void clickProjectManagement() {
//        projectManagement.click();
//    }
//
//    public void clickTaskAndTimeSubMenu() {
//        taskAndTimeSubMenu.click();
//    }

    // Add more methods as needed

}
