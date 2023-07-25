package com.demo.testcases.web;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.actions.web.LoginPage;
import com.demo.setup.BaseSelenium;
import com.github.dockerjava.api.model.Info;

public class WebLoginTest extends BaseSelenium {
	Logger logger = Logger.getLogger(WebLoginTest.class);

	@Test(priority = 1)
	public void login() throws Exception {
		LoginPage page = new LoginPage(driver);
		page.enterValidEmail();
		page.enterValidPassword();
		page.click_login_btn();
		page.enter_otp();
		page.verify_otp();
		page.implicitWait();
		logger.info("Login test cases executed successfully");
	}

	@Test(priority = 2, enabled = false)
	public void Logout() throws InterruptedException {
		LoginPage page = new LoginPage(driver);
		page.click_arrowicon();
		page.click_logout_btn();
		page.click_conformation_Logout_btn();
		WebElement recent_login = page.recent_login_disply();
		logger.info("Verifying the recent login text is displayed");
		Assert.assertTrue(recent_login.isDisplayed(), "Recent login is not displaying on the page");
		logger.info("Logout test cases executed successfully");
	}

	@Test(priority = 3, enabled = false)
	public void validate_error_message() throws Exception {
		LoginPage page = new LoginPage(driver);
		page.enterValidEmail();
		page.enterInvalidPassword();
		page.click_login_btn();
		String actualErrorMessage = page.validateErrorMessage();
		AssertJUnit.assertEquals(actualErrorMessage, "The password that you've entered is incorrect. Forgotten password?");
	}

}
