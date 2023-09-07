package com.demo.testcases.web;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.demo.actions.web.CFDPage;
import com.demo.actions.web.Instructions;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.ExcelDataProvider;

public class WebInstructionsPageTest extends BaseSelenium {
	
	@Test(priority = 1, groups="web" )
	public void instructions_tab() throws InterruptedException {
		CFDPage page = new CFDPage(driver);
		page.open_instruction_tab();
	}
	
	@Test(priority = 2,dataProviderClass=ExcelDataProvider.class,dataProvider="dp" )
	public void test_instruction_procedure(Hashtable<String,String> data) throws Exception {
		Instructions instruction = new Instructions(driver);
		instruction.search_test(data.get("searched_test"));
		instruction.add_test(data.get("searched_test"));
		
		instruction.search_instruction(data.get("searched_instruction"));
		instruction.add_instruction(data.get("searched_instruction"));
		
		instruction.search_procedure(data.get("searched_procedure"));
		instruction.add_procedure(data.get("searched_procedure"));
		
		instruction.create_new_test(data.get("created_test"));
		instruction.create_new_instruction(data.get("created_instruction"));
		instruction.create_new_procedure(data.get("created_procedure"));
	}
}
