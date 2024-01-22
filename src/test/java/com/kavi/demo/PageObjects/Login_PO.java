package com.kavi.demo.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_PO {

	WebDriver ldriver;

	// Constructor to initialize the WebDriver and PageFactory
	public Login_PO(WebDriver rdriver) {
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);
	}

	// Method to interact with the username input field
	@FindBy(id = "outlined-uncontrolled")
	WebElement username;

	public void enterusername(String id) {
		username.sendKeys(id);

	}

	// Method to interact with the password input field
	@CacheLookup
	@FindBy(id = "password")
	public WebElement passwordInput;

	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}

	// Method to click the login button
	@CacheLookup
	@FindBy(xpath = "//*[@id=\"loginform\"]/div[3]/input")
	private WebElement loginButton;

	public void clickLoginButton() {
		loginButton.click();
	}

	@CacheLookup
	@FindBy(xpath = "//span[@class=\"ant-avatar ant-avatar-circle ant-avatar-icon ant-dropdown-trigger css-dev-only-do-not-override-mxhywb\"]")
	public WebElement icon;

	public void clickiconButton() {
		icon.click();
	}

	@CacheLookup
	@FindBy(xpath = "/html/body/div[2]/div/div/ul/li[2]/span")
	public WebElement logout;

	public void clicklogoutButton() {
		logout.click();
	}

	public void clear() {
		username.clear();
		passwordInput.clear();
	}

}
