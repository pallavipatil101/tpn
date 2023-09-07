package com.demo.testcases.web;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.demo.actions.web.CFDPage;
import com.demo.actions.web.SummaryPage;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.ExcelDataProvider;
import com.demo.utilities.WebUtilities;

public class WebSummaryPageTest extends BaseSelenium {
	Logger logger = Logger.getLogger(WebLoginTest.class);
	WebUtilities utilities = new WebUtilities();
	
	@Test(priority = 1, groups="web")
	public void summary_tab() throws InterruptedException {
		CFDPage page = new CFDPage(driver);
		page.open_summary_tab();
	}
	
	@Test(priority = 2, dataProviderClass=ExcelDataProvider.class,dataProvider="dp")
	public void test_instruction_procedure(Hashtable<String,String> data) throws Exception {
		SummaryPage summary =  new SummaryPage(driver);
		summary.verify_tests_in_summary(data.get("searched_test"), data.get("created_test"));
		summary.verify_instructions_in_summary(data.get("searched_instruction"), data.get("created_instruction"));
		summary.verify_procedures_in_summary(data.get("searched_procedure"), data.get("created_procedure"));
	}
	
	@Test(priority = 3, dataProviderClass=ExcelDataProvider.class,dataProvider="dp")
	public void add_drugs(Hashtable<String,String> data) throws Exception {
		SummaryPage summary =  new SummaryPage(driver);
		summary.verify_drugs_in_summary(data.get("popular_drug"), data.get("searched_drug"));
	}
	
	@Test(priority = 4, dataProviderClass=ExcelDataProvider.class,dataProvider="dp")
	public void add_new_drug(Hashtable<String,String> data) {
		SummaryPage summary =  new SummaryPage(driver);
		summary.verify_created_drug_in_summary(data.get("new_drug_name"));
	}
	
	@Test(priority = 5, dataProviderClass=ExcelDataProvider.class,dataProvider="dp")
	public void drugs_frequency(Hashtable<String,String> data) throws Exception {
		SummaryPage summary =  new SummaryPage(driver);
		summary.verify_drug_frequency(data.get("popular_drug_frequency"), data.get("created_drug_frequency"), data.get("searched_drug_frequency"));
		summary.verify_drug_timings(data.get("frequency_morning"), data.get("frequency_afternoon"), data.get("frequency_evening"), data.get("frequency_night"));
	}
	
	@Test(priority = 6, dataProviderClass=ExcelDataProvider.class,dataProvider="dp")
	public void add_cfd(Hashtable<String,String> data) throws Exception {
		SummaryPage summary =  new SummaryPage(driver);
		summary.verify_complaint_finding_in_summary(data.get("searched_complaint"), data.get("searched_finding"));
		summary.verify_diagnosis_in_summary(data.get("popular_diagnosis"), data.get("searched_diagnosis"));
	}
	
	@Test(priority = 7, dataProviderClass=ExcelDataProvider.class,dataProvider="dp")
	public void saved_record(Hashtable<String,String> data) throws Exception {
		SummaryPage summary =  new SummaryPage(driver);
		summary.verify_record_is_saved(data.get("record_date"));
	}
		
	@Test(priority = 8)
	public void verify_record_is_printed() throws Exception {
		SummaryPage summary =  new SummaryPage(driver);
		summary.verify_record_can_be_printed();
	}
}
