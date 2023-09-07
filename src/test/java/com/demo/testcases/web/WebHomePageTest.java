package com.demo.testcases.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.demo.actions.web.CFDPage;
import com.demo.actions.web.HomePage;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.ExcelDataProvider;
import com.demo.utilities.WebUtilities;

public class WebHomePageTest extends BaseSelenium {
	Logger logger = Logger.getLogger(WebLoginTest.class);
	WebUtilities utilities = new WebUtilities();

	@Test(priority = 1, groups = "web")
	public void select_evening_slot() throws InterruptedException {
		HomePage page = new HomePage(driver);
		Thread.sleep(4000);
		page.select_evening_time();
		Thread.sleep(2000);
	}
	
	@Test(priority = 2, description = "Verify doctor's should be able to create appointment for the available appointment slot.", dataProviderClass=ExcelDataProvider.class,dataProvider="dp")
	public void appointment(Hashtable<String,String> data) throws Exception {
		HomePage page = new HomePage(driver);
		Thread.sleep(4000);
		page.select_a_slot();
		Thread.sleep(4000);
		page.enter_mobile_no(data.get("Patient_Mobile"));
		System.out.println(data.get("Patient_Mobile"));
		//page.choose_patient();
		page.enter_patient_name(data.get("Patient_Name"));
		page.enter_patient_age(data.get("Patient_Age"));
		Thread.sleep(4000);
		page.select_patient_gender(data.get("Patient_Gender"));
		page.click_on_Add_Appointment_Button();
		page.verify_booked_slot(data.get("Patient_Mobile"));
		logger.info("Appointment Taken successfully");
		}
	
	@Test(priority = 3,description = "Verify if appointment slot is expired & doctor shouldn't able to create appointment for the expired slot .", enabled = false)
	public void verify_disabled_slot() throws ParseException {
		HomePage page = new HomePage(driver);
		page.verify_diabled_slot();
	}
}
	
