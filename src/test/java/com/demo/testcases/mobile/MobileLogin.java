package com.demo.testcases.mobile;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.demo.actions.mobile.MobileLoginPage;
import com.demo.setup.BaseSelenium;
import com.demo.testcases.web.WebLoginTest;

public class MobileLogin extends BaseSelenium{
	
	Logger logger = Logger.getLogger(WebLoginTest.class);

	@Test
	public void mobileLogin() {
		
		MobileLoginPage mobileLoginPage = new MobileLoginPage(appiumDriver);
		
		mobileLoginPage.type_username();
		
		mobileLoginPage.click_password();
		mobileLoginPage.type_password();
		
		mobileLoginPage.click_login_button();
	}
}
