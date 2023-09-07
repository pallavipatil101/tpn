package com.demo.actions.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.demo.setup.BaseSelenium;
import com.demo.testcases.web.WebLoginTest;
import com.demo.utilities.WebUtilities;

public class Instructions extends BaseSelenium {
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();
	Logger logger = Logger.getLogger(WebLoginTest.class);
	String backgroundColor;
	
	public Instructions(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//label[@class='item item-input']/input")
	private WebElement searchbox;
	
	@FindBy(xpath = "//*[@class=\"search-result mb-0 ng-scope\"]/div/a")
	private List<WebElement> search_results;
	
	@FindBy(name = "test_search")
	private WebElement test_searchbox;
	
	@FindBy(xpath = "//*[@class = 'icon-check-circle forestgreen']")
	private WebElement new_test_inst_proc_checkbox;
	
	@FindBy(name = "procedure_search")
	private WebElement procedure_searchbox;
		
	@FindBy(name = "inst_search")
	private WebElement instruction_searchbox;
	
	
	public void search_test(String searchedTest) throws Exception {
		utilities.sendkeys(searchbox, searchedTest);
		Thread.sleep(3000);

	}
	
	public void add_test(String searchedTest) throws Exception {
		for(WebElement result : search_results)
		{
				try {
					if(result.getText().contains(searchedTest))
					{
						utilities.verify_true(result.getText().contains(searchedTest));
						utilities.moveAndClick(result);
					    break;
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					try {
						if(result.getText().contains(searchedTest))
						{
							utilities.verify_true(result.getText().contains(searchedTest));
							utilities.moveAndClick(result);
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains(searchedTest))
						{
							utilities.verify_true(result.getText().contains(searchedTest));
							utilities.moveAndClick(result);
						    break;
						}					}
				}
		}
	}
	
	public void search_instruction(String searchedInstruction) throws Exception {
		searchbox.clear();
		utilities.sendkeys(searchbox, searchedInstruction);
		Thread.sleep(3000);
	}
	
	public void add_instruction(String searchedInstruction) throws Exception {
		for(WebElement result : search_results)
		{
			//backgroundColor = result.getCssValue("background-color");
				try {
					if(result.getText().contains(searchedInstruction))
					{
						utilities.verify_true(result.getText().contains(searchedInstruction));
						utilities.moveAndClick(result);
					    break;
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					try {
						if(result.getText().contains(searchedInstruction))
						{
							utilities.verify_true(result.getText().contains(searchedInstruction));
							utilities.moveAndClick(result);
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains(searchedInstruction))
						{
							utilities.verify_true(result.getText().contains(searchedInstruction));
							utilities.moveAndClick(result);
						    break;
						}
					}
				}
		}
	}
	
	public void search_procedure(String searchedProcedure) throws Exception {
		searchbox.clear();
		utilities.sendkeys(searchbox, searchedProcedure);
		Thread.sleep(3000);
	}
	
	public void add_procedure(String searchedProcedure) throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");

		for(WebElement result : search_results)
		{
			//backgroundColor = result.getCssValue("background-color");
				try {

					if(result.getText().contains(searchedProcedure))
					{
						utilities.verify_true(result.getText().contains(searchedProcedure));
						utilities.moveAndClick(result);
					    break;
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					try {
						if(result.getText().contains(searchedProcedure))
						{
							utilities.verify_true(result.getText().contains(searchedProcedure));
							utilities.moveAndClick(result);
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains(searchedProcedure))
						{
							utilities.verify_true(result.getText().contains(searchedProcedure));
							utilities.moveAndClick(result);
						    break;
						}
					}
				}
		}
	}
	
	public void create_new_test(String createdTest) throws Exception {
		utilities.sendkeys(test_searchbox, createdTest);
		utilities.click(new_test_inst_proc_checkbox);
	}
	
	public void create_new_instruction(String createdInstruction) throws Exception {
		utilities.sendkeys(instruction_searchbox, createdInstruction);
		utilities.scroll_into_view(instruction_searchbox);
		utilities.click(new_test_inst_proc_checkbox);
	}
	
	public void create_new_procedure(String createdProcedure) throws Exception {
		utilities.sendkeys(procedure_searchbox, createdProcedure);
		utilities.scroll_into_view(procedure_searchbox);
		utilities.click(new_test_inst_proc_checkbox);
	}
	
}
