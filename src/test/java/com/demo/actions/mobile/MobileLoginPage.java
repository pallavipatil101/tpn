package com.demo.actions.mobile;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.setup.BaseSelenium;
import com.demo.utilities.MobileUtilities;

import io.appium.java_client.AppiumDriver;

public class MobileLoginPage extends BaseSelenium{
	
	public AppiumDriver appiumDriver;
	MobileUtilities utilities = new MobileUtilities();

	@FindBy(xpath= "//*[contains(@text,\"Username\")]")
	private WebElement userName;
	
	@FindBy(xpath= "//*[contains(@text,\"Password\")]")
	private WebElement password;
	
	@FindBy(xpath= "//*[@content-desc=\"Login\"]")
	private WebElement login;
	
	public void type_username() {
		utilities.sendkeys(userName, "Robeca J");
	}
	
	public void type_password() {
		utilities.sendkeys(password, "dukadirect@admin");
	}
	
	public void click_login_button() {
		utilities.click(login);
	}
	
	public void click_password() {
		utilities.click(password);
	}
	
	public MobileLoginPage(AppiumDriver appiumdriver) {

		this.appiumDriver = appiumdriver;
		PageFactory.initElements(appiumDriver, this);

	}
}
