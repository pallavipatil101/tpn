package com.demo.testcases.web;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.demo.actions.web.CFDPage;
import com.demo.actions.web.HomePage;
import com.demo.actions.web.SummaryPage;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class WebAppointmentSummaryPageTest extends BaseSelenium {
	Logger logger = Logger.getLogger(WebLoginTest.class);
	WebUtilities utilities = new WebUtilities();
	
	@Test(priority = 1, enabled = true)
	public void summary_tab() throws InterruptedException {
		CFDPage page = new CFDPage(driver);
		page.open_summary_tab();
	}
	
	@Test(priority = 2, enabled = true)
	public void verify_dagnosis() throws Exception {
		SummaryPage summary =  new SummaryPage(driver);
		//summary.verify_diagnosis_in_summary();
		summary.verify_tests__in_summary();
		summary.verify_instructions_in_summary();
		summary.verify_procedures_in_summary();
	}
	
	@Test(priority = 3, enabled = true)
	public void verify_drug() throws Exception {
		SummaryPage summary =  new SummaryPage(driver);
		summary.verify_drugs_in_summary();
		summary.verify_drug_frequency();
	}
	
	@Test(priority = 4, enabled = true)
	public void verify_record_is_saved() throws Exception {
		SummaryPage summary =  new SummaryPage(driver);
		summary.verify_record_is_saved();
	}
	
	@Test(priority = 5, enabled = true)
	public void verify_record_is_printed() throws Exception {
		SummaryPage summary =  new SummaryPage(driver);
		summary.verify_record_can_be_printed();
	}
}
