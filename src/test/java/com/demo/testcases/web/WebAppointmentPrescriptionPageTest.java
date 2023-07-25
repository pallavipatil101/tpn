package com.demo.testcases.web;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.demo.actions.web.Prescription;
import com.demo.actions.web.CFDPage;
import com.demo.actions.web.HomePage;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class WebAppointmentPrescriptionPageTest extends BaseSelenium {
	Logger logger = Logger.getLogger(WebLoginTest.class);
	WebUtilities utilities = new WebUtilities();
	
	@Test(priority = 1, enabled = true)
	public void prescription_tab() throws InterruptedException {
		CFDPage page = new CFDPage(driver);
		page.open_prescription_tab();
	}
	
	@Test(priority = 2, enabled = true)
	public void verify_20_popular_drugs() throws Exception {
		Prescription prescription = new Prescription(driver);
		prescription.verify_20_popular_drugs();
		prescription.verify_popular_drugs_delete_icon();
		prescription.add_popular_drug();
		prescription.verify_added_popular_drug();
		//prescription.delete_added_drug();
	}
	
	@Test(priority = 3, enabled = true)
	public void search_drugs() throws Exception {
		Prescription prescription = new Prescription(driver);
		prescription.search_drugs();
		prescription.add_searched_drug();
		prescription.verify_added_searched_drug();
	}
	
	@Test(priority = 4, enabled = true)
	public void add_new_drug_form_details() throws Exception {
		Prescription prescription = new Prescription(driver);
		prescription.click_on_add_new_drug_btn();
		prescription.create_new_drug_details();
		prescription.create_new_drug_strength();
		prescription.create_new_drug_frequency();
		prescription.create_new_drug_duration();
		prescription.craete_new_drug_relation_with_food();
		prescription.create_drug_instructions();
		prescription.click_submit_btn();
	}
	
	@Test(priority = 5, enabled = true)
	public void verify_created_drug_is_added() throws Exception {
		Prescription prescription = new Prescription(driver);
		prescription.verify_created_drug_added();
		prescription.verify_created_drug_appears_in_search();
	}
	
	@Test(priority = 6, enabled = true)
	public void verify_delete_icon_for_added_drugs() {
		Prescription prescription = new Prescription(driver);
		prescription.verify_delete_icon_for_added_drug();
	}
	
	@Test(priority = 7, enabled = true)
	public void verify_same_drug_cannot_be_added_twice() throws Exception {
		Prescription prescription = new Prescription(driver);
		prescription.verify_same_drug_cannot_be_added_twice();
	}
	
	@Test(priority = 8, enabled = true)
	public void delete_all_added_drugs() {
		Prescription prescription = new Prescription(driver);
		prescription.delete_all_drugs();
	}
	
	
}
