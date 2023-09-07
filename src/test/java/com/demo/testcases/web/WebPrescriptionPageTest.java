package com.demo.testcases.web;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.demo.actions.web.CFDPage;
import com.demo.actions.web.Prescription;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.ExcelDataProvider;
import com.demo.utilities.WebUtilities;

public class WebPrescriptionPageTest extends BaseSelenium {
	Logger logger = Logger.getLogger(WebLoginTest.class);
	WebUtilities utilities = new WebUtilities();
	
	@Test(priority = 1, groups="web")
	public void prescription_tab() throws Exception {
		CFDPage page = new CFDPage(driver);
		page.open_prescription_tab();
	}
	
	@Test(priority = 2)
	public void verify_20_popular_drugs() throws Exception {
		Prescription prescription = new Prescription(driver);
		prescription.verify_20_popular_drugs();
		prescription.verify_popular_drugs_delete_icon();
	}
	
	@Test(priority = 3, dataProviderClass=ExcelDataProvider.class,dataProvider="dp")
	public void add_drugs(Hashtable<String,String> data) throws Exception {
		Prescription prescription = new Prescription(driver);
		prescription.add_popular_drug(data.get("popular_drug"));
		prescription.verify_added_popular_drug(data.get("popular_drug"));
		//prescription.delete_added_drug();
		prescription.search_drugs(data.get("searched_drug"));
		prescription.add_searched_drug(data.get("searched_drug"));
		prescription.verify_added_searched_drug(data.get("searched_drug"));
	}
	
	@Test(priority = 4, dataProviderClass=ExcelDataProvider.class,dataProvider="dp")
	public void add_new_drug(Hashtable<String,String> data) throws Exception {
		Prescription prescription = new Prescription(driver);
		prescription.click_on_add_new_drug_btn();
		prescription.create_new_drug_details(data.get("new_drug_name"), data.get("new_drug_company"), data.get("new_drug_price"), data.get("new_drug_mode"), data.get("new_drug_formulation"));
		prescription.create_new_drug_strength(data.get("new_drug_strength"));
		prescription.create_new_drug_frequency(data.get("new_drug_morning"), data.get("new_drug_afternoon"), data.get("new_drug_evening"));
		prescription.create_new_drug_duration(data.get("new_drug_duration"), data.get("new_drug_duration_in"));
		prescription.craete_new_drug_relation_with_food(data.get("new_drug_food_ralation"));
		prescription.create_drug_instructions(data.get("new_drug_instructions"));
		prescription.click_submit_btn();
		prescription.verify_created_drug_added(data.get("new_drug_name"));
		prescription.verify_created_drug_appears_in_search(data.get("new_drug_name"));
		prescription.verify_same_drug_cannot_be_added_twice(data.get("new_drug_name"));

	}
	
	@Test(priority = 5)
	public void verify_delete_icon_for_added_drugs() {
		Prescription prescription = new Prescription(driver);
		prescription.verify_delete_icon_for_added_drug();
	}
	
	@Test(priority = 6)
	public void delete_all_added_drugs() {
		Prescription prescription = new Prescription(driver);
		prescription.delete_all_drugs();
	}
	
}
