package com.demo.actions.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.demo.setup.BaseSelenium;
import com.demo.testcases.web.WebLoginTest;
import com.demo.utilities.WebUtilities;

public class Prescription extends BaseSelenium {
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();
	Logger logger = Logger.getLogger(WebLoginTest.class);
	String newdrug;
	public Prescription(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//label[@class='item item-input']/input")
	private WebElement searchbox;
	
	@FindBy(xpath = "//*[@class=\"search-result ng-scope\"]/div/a")
	private List<WebElement> search_result_drugs;
	
	@FindBy(xpath= "//*[@class=\"dropdown flex-100 border-0\"]/a/span")
	//@FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div[2]/div[2]/section/div[3]/div[1]/div[2]/div/div")
	private List<WebElement> added_drugs_list;
	
	//@FindBy(xpath = "//*[@ng-click=\"vm.deleteDrug(drug, $index)\"]")
	@FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div[2]/div[2]/section/div[3]/div[1]/div[2]/div[1]/div/div[10]/div/div[2]/div/a/i")
	private WebElement delete_drugs_icon;
	
	//@FindBy(xpath = "delete-block ng-scope")
	@FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div[1]/div[2]/div[3]/a/i")
	private WebElement delete_all_drugs_icon;
	
	@FindBy(xpath = "//*[@class=\"btn btn-blue\"]")
	private WebElement add_new_drug_btn;
	
	@FindBy(name = "brand_name_0")
	private WebElement new_drug_name;
	
	@FindBy(name = "company_name_0")
	private WebElement new_drug_company_name;
	
	@FindBy(name = "drug_mrp")
	private WebElement new_drug_price;
	
	@FindBy(css = ".md-primary.flex-50.ml-0.ng-scope")
	private List<WebElement> new_drug_mode;
	
	@FindBy(css = ".drug-formulation.ng-scope")
	private List<WebElement> new_drug_formulation;;

	@FindBy(name = "strength_md_0")
	private WebElement new_drug_strength;
	
	@FindBy(id = "select_value_label_321")
	private WebElement new_drug_strength_unit_dropdown_btn;
	
	@FindBy(xpath = "//*[@class=\"md-select-menu-container md-active md-clickable\"]/md-select-menu/md-content/md-option")
	private List<WebElement> drug_strength_unit_drop_options;
	
	@FindBy(xpath = "//*[@class = \"flex-5 layout-column\"]/span/md-checkbox")
	private List<WebElement> drug_frequency_checknboxes;
	
	@FindBy(name = "duration")
	private WebElement drug_duration;
	
	@FindBy(name = "duration_type")
	private WebElement drug_duration_type_dropdown_btn;
	
	@FindBy(xpath = "//*[@name=\"food_relation\"]/md-radio-button")
	private List<WebElement> food_relation_radios;
	
	@FindBy(name = "drug_inst_search")
	private WebElement drug_instruction_searchbox;
	
	@FindBy(xpath = "//*[@class=\"md-autocomplete-suggestions\"]/li")
	private List<WebElement> drug_instruction_search_result;
	
	@FindBy(css = ".check-wrap.font-large")
	private WebElement add_new_instruction_checkbox;
	
	@FindBy(xpath = "//*[@class ='ng-scope layout-align-end-end layout-row']/button")
	private WebElement new_drug_form_next_btn_page1;
	
	@FindBy(xpath = "//*[@layout-align ='space-between center']/button[2]")
	private WebElement new_drug_form_next_btn_page2;

	@FindBy(xpath = "//*[@layout-align ='space-between center']/button[1]")
	private WebElement new_drug_form_previous_btn_page2;
	
	@FindBy(xpath = "//*[@layout-align ='space-between center mt-5']/button[1]")
	private WebElement new_drug_form_previous_btn_page3;

	@FindBy(xpath = "//*[@layout-align ='space-between center mt-5']/button[2]")
	private WebElement new_drug_form_submit_btn_page3;
	
	@FindBy(xpath = "//div[@class='md-toast-content']/span")
	private WebElement drug_already_added_msg;
	
	public void verify_20_popular_drugs() {
		utilities.implicitWait();
		int count = 0;
		for(WebElement result : search_result_drugs)
		{
			utilities.explicitwait(result);
			if(result.isDisplayed())
			{
				count++;
			}
		}
		softAssert.assertTrue(count==20);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>20 popular drug count: "+count);

	}
	
	public void verify_popular_drugs_delete_icon() {
		utilities.scroll_into_view(delete_all_drugs_icon);
		utilities.verify_true(delete_all_drugs_icon.isDisplayed());
		utilities.scroll_up();
	}
	
	public void add_popular_drug(String popularDrug) throws Exception {		
		for(WebElement result : search_result_drugs)
		{
			Thread.sleep(3000);
				try {
					if(result.getText().contains(popularDrug))
					{
						result.click();
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					try {
						if(result.getText().contains(popularDrug))
						{
							result.click();

						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains(popularDrug))
						{
							result.click();
						}
					}
				}
		}
	}
	
	public void verify_added_popular_drug(String popularDrug) throws Exception {
		for(WebElement added_drug : added_drugs_list)
		{
			utilities.verify_true(added_drug.getText().contains(popularDrug));

		}
	}
	
	public void verify_added_searched_drug(String searchedDrug) throws Exception {
		for(int i = 0; i<added_drugs_list.size(); i++ )
		{
			while(i == 1)
			{
				utilities.verify_true(added_drugs_list.get(i).getText().contains(searchedDrug));
		
			}
		}
	}
	
	public void verify_delete_icon_for_added_drug() {
		utilities.verify_true(delete_drugs_icon.isDisplayed());

	}
	
	public void delete_added_drug() {
		utilities.click(delete_drugs_icon);
	}
	
	public void delete_all_drugs() {
		utilities.click(delete_all_drugs_icon);
	}
	
	public void search_drugs(String searchedDrug) throws Exception {
		utilities.sendkeys(searchbox, searchedDrug);
		Thread.sleep(3000);
	}
	
	public void add_searched_drug(String searchedDrug) throws Exception {
		for(WebElement result : search_result_drugs)
		{
			utilities.explicitwait(result);

				try {
					if(result.getText().contains(searchedDrug))
					{
						utilities.verify_true(result.getText().contains(searchedDrug));
						utilities.moveAndClick(result);
					    break;
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					try {
						if(result.getText().contains(searchedDrug))
						{
							utilities.verify_true(result.getText().contains(searchedDrug));
							utilities.moveAndClick(result);
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains(searchedDrug))
						{
							utilities.verify_true(result.getText().contains(searchedDrug));
							utilities.moveAndClick(result);
						    break;
						}
					}
				}
		}
	
	
	}
	
	public void click_on_add_new_drug_btn() throws InterruptedException {
		utilities.click(add_new_drug_btn);
		Thread.sleep(3000);
	}
	
	public void create_new_drug_details(String newdrugName, String newdrugCompany,String newdrugPrice, String newdrugMode, String newdrugFormulation  ) throws Exception {
		utilities.sendkeys(new_drug_name, newdrugName);
		utilities.sendkeys(new_drug_company_name, newdrugCompany);
		utilities.sendkeys(new_drug_price, newdrugPrice);
		
		for(WebElement drug_mode : new_drug_mode)
		{
			if(drug_mode.getText().equalsIgnoreCase(newdrugMode))
			{
				utilities.click(drug_mode);
			}
		}
		
		for(WebElement drug_form : new_drug_formulation)
		{
			if(drug_form.getText().equalsIgnoreCase(newdrugFormulation))
			{
				utilities.click(drug_form);
			}
		}
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", new_drug_form_next_btn_page1);
		Actions action = new Actions(driver);
		action.moveToElement(new_drug_form_next_btn_page1).click().perform();
		Thread.sleep(1000);
	}
	
	public void create_new_drug_strength(String newdrugStrength) throws Exception {
		utilities.sendkeys(new_drug_strength, newdrugStrength );
		
//		utilities.click(new_drug_strength_unit_dropdown_btn);
//		for(WebElement strength_unit : drug_strength_unit_drop_options)
//		{
//			if(strength_unit.getText().equalsIgnoreCase("GM"))
//			{
//				utilities.click(strength_unit);
//			}
//		}
		
		utilities.scroll_into_view(new_drug_form_next_btn_page1);
		Actions action = new Actions(driver);
		action.moveToElement(new_drug_form_next_btn_page2).click().perform();
	}
	
	public void create_new_drug_frequency(String frequency1, String frequency2, String frequency3) {
		for(WebElement drug_frequency : drug_frequency_checknboxes)
		{
			if(drug_frequency.getAttribute("name").equals(frequency1)||drug_frequency.getAttribute("name").equals(frequency2)||drug_frequency.getAttribute("name").equals(frequency3))
			{
				utilities.javascript_click(drug_frequency);
			}
		}
	}
	
	public void create_new_drug_duration(String newdrugDuration,String newdrugDurationype) throws Exception {
		utilities.sendkeys(drug_duration, newdrugDuration);
		utilities.click(drug_duration_type_dropdown_btn);
		Select select = new Select(drug_duration_type_dropdown_btn);
		select.selectByVisibleText(newdrugDurationype);
	}
	
	public void craete_new_drug_relation_with_food(String foodRelation) throws Exception {
		for(WebElement food_relation : food_relation_radios)
		{
			if(food_relation.getText().equalsIgnoreCase(foodRelation))
			{
				utilities.click(food_relation);
			}
		}
	}
	
	public void create_drug_instructions(String newDrugInstruction) throws Exception {
		utilities.sendkeys(drug_instruction_searchbox, newDrugInstruction);
		for(WebElement instruction : drug_instruction_search_result)
		{
			if(instruction.getText().equalsIgnoreCase(newDrugInstruction))
			{
				utilities.click(instruction);
			}
		}
	}
	
	public void click_submit_btn() {
		utilities.click(new_drug_form_submit_btn_page3);
	}
	
	public void verify_created_drug_added(String newdrugName) throws Exception {
		Thread.sleep(2000);
		for(WebElement added_drug : added_drugs_list)
		{
			synchronized (added_drug) {
	            try {
	            	added_drug.wait(5000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
			Thread.sleep(3000);
//			System.out.println(">>>>>>>>DRuG NAME ADDED GET TEXT: "+added_drug.getText());
			
			List<WebElement> drugList = driver.findElements(By.xpath("//*[@class='dropdown flex-100 border-0']/a/span"));
			for(int i=0; i<drugList.size(); i++)
			{
				if(i==2)
				{
					utilities.verify_true(drugList.get(i).getText().contains(newdrugName));
				}
			}
		}
	}
	
	public void verify_created_drug_appears_in_search(String newdrugName) throws Exception {
		searchbox.clear();
		utilities.sendkeys(searchbox, newdrugName);
		Thread.sleep(4000);
		for(WebElement result : search_result_drugs)
		{
				try 
				{
					utilities.verify_true(result.getText().contains(newdrugName));
						
				}

				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					utilities.verify_true(result.getText().contains(newdrugName));

				}
		}
		
	}
	
	public void verify_same_drug_cannot_be_added_twice(String newdrugName) throws Exception {
		searchbox.clear();
		utilities.sendkeys(searchbox, newdrugName);
		Thread.sleep(10000);
		for(int i = 0; i<search_result_drugs.size(); i++)
		{
			if(search_result_drugs.get(i).getText().contains(newdrugName.toUpperCase()))
			{
				utilities.click(search_result_drugs.get(i));
			    break;
			}

			utilities.verify_true(drug_already_added_msg.getText().contains("Drug already added"));	
		}
	
	}
}
