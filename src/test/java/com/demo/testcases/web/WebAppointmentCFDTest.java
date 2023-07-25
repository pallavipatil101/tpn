package com.demo.testcases.web;

import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.demo.actions.web.CFDPage;
import com.demo.actions.web.HomePage;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class WebAppointmentCFDTest extends BaseSelenium {
	Logger logger = Logger.getLogger(WebLoginTest.class);
	WebUtilities utilities = new WebUtilities();

	@Test(priority = 1, enabled = true, description = "Verify doctor's should be able to create appointment for the available appointment slot.")
	public void appointment_for_existing_patient() throws Exception {
		HomePage page = new HomePage(driver);
		Thread.sleep(5000);
		page.select_evening_time();
		utilities.implicitWait();
		page.select_a_slot();
		utilities.implicitWait();
		page.enter_mobile_no();
		utilities.implicitWait();
		//page.choose_patient();
		page.enter_patient_name();
		page.enter_patient_age();
		utilities.implicitWait();
		page.click_on_Add_Appointment_Button();
		logger.info("Appointment Taken successfully");
	}
	
	@Test(priority = 2, description = "Verify that doctor shouldn't able to create appointment in the already booked appointment slot.", enabled = false)
	public void verify_booked_slot() {
		HomePage page = new HomePage(driver);
		page.verify_booked_slot();
	}
	
	@Test(priority = 3, description = "Verify if appointment slot is expired slot should be disable for appointment creation & doctor shouldn't able to create appointment for the same expired slot .")
	public void verify_disabled_slot() throws ParseException {
		HomePage page = new HomePage(driver);
		page.verify_diabled_slot();
	}
	
	@Test(priority = 4)
	public void open_created_appointment() throws InterruptedException, IOException {
		HomePage page = new HomePage(driver);
		utilities.implicitWait();
		page.select_created_appointment();
	}
	
	@Test(priority = 5, enabled = true)
	public void verify_20_popular_diagnosis() {
		CFDPage page = new CFDPage(driver);
		page.verify_popular_diagnosis();
	}
	
	@Test(priority = 6, enabled = true)
	public void add_popular_diagnosis() throws Exception {
		CFDPage page = new CFDPage(driver);
		page.add_popular_diagnosis();
		page.verify_added_cfd();
	}
	
	@Test(priority = 7, enabled = false)
	public void delete_added_popular_diagnosis() {
		CFDPage page = new CFDPage(driver);
		page.delete_added_diagnosis();
	}
	
	@Test(priority = 8, enabled = true)
	public void diagnosis_tab_add_cfd() throws Exception {
		CFDPage page = new CFDPage(driver);
		page.search_diagnosis();
		page.search_complaint();
		page.search_finding();
	}
	
	@Test(priority = 9, enabled = true)
	public void diagnosis_tab_verify_added_cfd() throws Exception {
		CFDPage page = new CFDPage(driver);
		page.verify_added_cfd();
	}
	
	@Test(priority = 10, enabled = false)
	public void diagnosis_tab_delete_cfd() {
		CFDPage page = new CFDPage(driver);
		page.delete_added_diagnosis();
		page.delete_added_complaint();
//		page.delete_added_finding();	
	}
}
