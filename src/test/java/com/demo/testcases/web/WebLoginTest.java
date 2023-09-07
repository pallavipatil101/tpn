package com.demo.testcases.web;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.demo.actions.web.Login_Page;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.ExcelDataProvider;

public class WebLoginTest extends BaseSelenium {
	Logger logger = Logger.getLogger(WebLoginTest.class);


	@Test(priority = 1, groups = "web",dataProviderClass=ExcelDataProvider.class,dataProvider="dp" )
	public void login(Hashtable<String,String> data) throws Exception {
		Login_Page page = new Login_Page(driver);
		page.enter_Valid_Email(data.get("Username"));
		page.enter_Valid_Password(data.get("Password"));
		page.click_login_btn();
		page.enter_otp();
		page.verify_otp();
		page.implicit_Wait();
		logger.info("Login test cases executed successfully");
	}

	@Test(priority = 2, enabled = false)
	public void Logout() throws InterruptedException {
		Login_Page page = new Login_Page(driver);
		page.click_arrowicon();
		page.click_logout_btn();
		page.click_conformation_Logout_btn();
		WebElement recent_login = page.recent_login_disply();
		logger.info("Verifying the recent login text is displayed");
		Assert.assertTrue(recent_login.isDisplayed(), "Recent login is not displaying on the page");
		logger.info("Logout test cases executed successfully");
	}

	@Test(priority = 3, enabled = false, dataProviderClass=ExcelDataProvider.class,dataProvider="dp")
	public void validate_error_message(Hashtable<String,String> data) throws Exception {
		Login_Page page = new Login_Page(driver);
		page.enter_Valid_Email(data.get("Username"));
		page.enter_Invalid_Password(data.get("Password"));
		page.click_login_btn();
		String actualErrorMessage = page.validate_Error_Message();
		AssertJUnit.assertEquals(actualErrorMessage, "The password that you've entered is incorrect. Forgotten password?");
	}

}
