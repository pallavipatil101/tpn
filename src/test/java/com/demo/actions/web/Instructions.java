package com.demo.actions.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.setup.BaseSelenium;
import com.demo.testcases.web.WebLoginTest;
import com.demo.utilities.WebUtilities;

public class Instructions extends BaseSelenium {
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();
	Logger logger = Logger.getLogger(WebLoginTest.class);

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
	
	@FindBy(xpath = "//div[@ng-click = 'vm.addTestToDirectory(vm.new_test)']")
	private WebElement test_checkbox;
	
	@FindBy(name = "procedure_search")
	private WebElement procedure_searchbox;
	
	@FindBy(xpath = "//div[@ng-click = 'vm.addProcedureToDirectory(vm.new_procedure)']")
	private WebElement procedure_checkbox;
	
	@FindBy(name = "inst_search")
	private WebElement instruction_searchbox;
	
	@FindBy(xpath = "//div[@ng-click = 'vm.addInstToDirectory(vm.new_inst)']")
	private WebElement instruction_checkbox;
	
	public void search_test() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		utilities.sendkeys(searchbox, properties.getProperty("searched_test"));
		Thread.sleep(3000);

	}
	
	public void add_test() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		for(WebElement result : search_results)
		{
				try {
					if(result.getText().contains(properties.getProperty("searched_test")))
					{
						 Actions builder = new Actions(driver);
					     builder.moveToElement(result).click(result);
					     builder.perform();
						
					    break;
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					try {
						if(result.getText().contains(properties.getProperty("searched_test")))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
							
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains(properties.getProperty("searched_test")))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
						     
						    break;
						}
					}
				}
		}
	}
	
	public void search_instruction() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		searchbox.clear();
		utilities.sendkeys(searchbox, properties.getProperty("searched_instruction"));
		Thread.sleep(3000);
	}
	
	public void add_instruction() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");

		for(WebElement result : search_results)
		{
			String backgroundColor = result.getCssValue("background-color");
				try {
					if(result.getText().contains(properties.getProperty("searched_instruction")))
					{
						 Actions builder = new Actions(driver);
					     builder.moveToElement(result).click(result);
					     builder.perform();
						
					    break;
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					try {
						if(result.getText().contains(properties.getProperty("searched_instruction")))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
							
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains(properties.getProperty("searched_instruction")))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
						     
						    break;
						}
					}
				}
		}
	}
	
	public void search_procedure() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		searchbox.clear();
		utilities.sendkeys(searchbox, properties.getProperty("searched_procedure"));
		Thread.sleep(3000);
	}
	
	public void add_procedure() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");

		for(WebElement result : search_results)
		{
			String backgroundColor = result.getCssValue("background-color");
				try {

					if(result.getText().contains(properties.getProperty("searched_procedure")))
					{
						 Actions builder = new Actions(driver);
					     builder.moveToElement(result).click(result);
					     builder.perform();
						
					    break;
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					try {
						if(result.getText().contains(properties.getProperty("searched_procedure")))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
							
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains(properties.getProperty("searched_procedure")))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
						     
						    break;
						}
					}
				}
		}
	}
	
	public void create_new_test() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		utilities.sendkeys(test_searchbox, properties.getProperty("created_test"));
		utilities.click(test_checkbox);
	}
	
	public void create_new_instruction() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		utilities.sendkeys(instruction_searchbox, properties.getProperty("created_instruction"));
		utilities.click(instruction_checkbox);
	}
	
	public void create_new_procedure() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		utilities.sendkeys(procedure_searchbox, properties.getProperty("created_procedured"));
		utilities.click(procedure_checkbox);
	}
	
}
