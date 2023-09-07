package com.demo.testcases.web;

import java.io.IOException;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.demo.actions.web.CFDPage;
import com.demo.actions.web.HomePage;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.ExcelDataProvider;
import com.demo.utilities.WebUtilities;

public class WebCFDPageTest extends BaseSelenium{
	Logger logger = Logger.getLogger(WebLoginTest.class);
	WebUtilities utilities = new WebUtilities();
	
	@Test(priority = 1, groups="web", dataProviderClass=ExcelDataProvider.class,dataProvider="dp")
	public void appointment(Hashtable<String,String> data) throws InterruptedException, IOException {
		HomePage page = new HomePage(driver);
		utilities.implicitWait();
		page.select_created_appointment(data.get("Patient_Name"));
	}
	
	@Test(priority = 2)
	public void verify_20_popular_diagnosis() {
		CFDPage page = new CFDPage(driver);
		page.verify_20_popular_diagnosis();
	}
	
	@Test(priority = 3, dataProviderClass=ExcelDataProvider.class,dataProvider="dp")
	public void add_cfd(Hashtable<String,String> data) throws Exception {
		CFDPage page = new CFDPage(driver);
		page.add_popular_diagnosis(data.get("popular_diagnosis"));
		page.search_diagnosis(data.get("searched_diagnosis"));
		page.search_complaint(data.get("searched_complaint"));
		page.search_finding(data.get("searched_finding"));
		page.verify_added_cfd(data.get("popular_diagnosis"),data.get("searched_diagnosis"),data.get("searched_complaint"),data.get("searched_finding"));
	}
	
	@Test(priority = 4, enabled = false)
	public void delete_added_popular_diagnosis() {
		CFDPage page = new CFDPage(driver);
		page.delete_added_diagnosis();
	}
	
	@Test(priority = 5, enabled = false)
	public void diagnosis_tab_delete_cfd() {
		CFDPage page = new CFDPage(driver);
		page.delete_added_diagnosis();
		page.delete_added_complaint();
		page.delete_added_finding();	
	}
}
