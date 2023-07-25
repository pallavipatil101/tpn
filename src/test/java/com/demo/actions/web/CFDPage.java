package com.demo.actions.web;

import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.demo.setup.BaseSelenium;
import com.demo.testcases.web.WebLoginTest;
import com.demo.utilities.ExcelUtils;
import com.demo.utilities.WebUtilities;

public class CFDPage extends BaseSelenium{
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();
	Logger logger = Logger.getLogger(WebLoginTest.class);

	public CFDPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = "//label[@class='item item-input']/input")
	private WebElement searchbox;

	//@FindBy(xpath = "//a[@ng-class='vm.addClass(result)']")
	@FindBy(xpath = "//div[@ng-repeat=\"result in vm.search_results\"]/a")
	private List<WebElement> Search_Result_cfd;

	@FindBy(css = ".nav.nav-tabs>li")
	private List<WebElement> tabs;

	@FindBy(css = ".field-wrap.field-name.ng-binding")
	private List<WebElement> added_cfd_list;

	@FindBy(xpath = "//*[@class= 'flex-15 layout-row field-wrap']/a[3]")
	private WebElement delete_diagnosis;

	@FindBy(xpath = "//*[@class= 'flex-30 field-wrap']/a[3]")
	private WebElement delete_complaint;

	@FindBy(xpath = "//*[@class= 'flex-15']/a[3]")
	private WebElement delete_finding;

	@FindBy(css = "md-toast > div > span")
	private WebElement deleted_cfd_msg;
	
	@FindBy(xpath = "//a[contains(text(),'INSTRUCTIONS')]")
	private WebElement Instruction_tab;
	
	public void verify_popular_diagnosis() {
		utilities.implicitWait();
		int count = 0;
		for (WebElement result : Search_Result_cfd) {
			utilities.fluent_wait(result);
			if (result.isDisplayed()) {
				count++;
			}
		}

		logger.info("Number of popular diagnosis is: "+count);
		Assert.assertTrue(count == 20);
	}

	public void add_popular_diagnosis() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		for (WebElement result : Search_Result_cfd) {
			utilities.explicitwait(result);
			try {

				if (result.getText().contains(properties.getProperty("popular_diagnosis"))) {
					Actions builder = new Actions(driver);
					builder.moveToElement(result).click(result);
					builder.perform();
					Assert.assertTrue(result.getText().contains(properties.getProperty("popular_diagnosis")));
					break;
				}
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				try {
					if (result.getText().contains(properties.getProperty("popular_diagnosis"))) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().contains(properties.getProperty("popular_diagnosis")));
						break;
					}
				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (result.getText().contains("popularDiagnosis")) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().contains(properties.getProperty("popular_diagnosis")));
						break;
					}
				}
			}
		}
	}

	public void search_diagnosis() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		utilities.implicitWait();
		utilities.sendkeys(searchbox, properties.getProperty("searched_diagnosis"));
		Thread.sleep(3000);
			try {
				for (WebElement result : Search_Result_cfd) {
				if (result.getText().equalsIgnoreCase(properties.getProperty("searched_diagnosis"))) {
					Actions builder = new Actions(driver);
					builder.moveToElement(result).click(result);
					builder.perform();
					Assert.assertTrue(result.getText().equalsIgnoreCase(properties.getProperty("searched_diagnosis")));
					break;
				}
				}
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				try {
					for (WebElement result : Search_Result_cfd) {
					if (result.getText().equalsIgnoreCase(properties.getProperty("searched_diagnosis"))) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().equalsIgnoreCase(properties.getProperty("searched_diagnosis")));
						break;
					}
					}
				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					for (WebElement result : Search_Result_cfd) {
					if (result.getText().equalsIgnoreCase(properties.getProperty("searched_diagnosis"))) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().equalsIgnoreCase(properties.getProperty("searched_diagnosis")));
						break;
					}
					}
				}
			}
		
	}

	public void search_complaint() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		searchbox.clear();
		utilities.sendkeys(searchbox, properties.getProperty("searched_complaint"));
		Thread.sleep(3000);
		
		for (WebElement result : Search_Result_cfd) {
			try {
				if (result.getText().equalsIgnoreCase(properties.getProperty("searched_complaint"))) {
					Actions builder = new Actions(driver);
					builder.moveToElement(result).click(result);
					builder.perform();
					Assert.assertTrue(result.getText().equalsIgnoreCase(properties.getProperty("searched_complaint")));
					break;
				}
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				try {
					if (result.getText().equalsIgnoreCase(properties.getProperty("searched_complaint")) ){
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().equalsIgnoreCase(properties.getProperty("searched_complaint")));
						break;
					}
				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (result.getText().equalsIgnoreCase(properties.getProperty("searched_complaint"))) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().equalsIgnoreCase(properties.getProperty("searched_complaint")));
						break;
					}
				}
			}
		}
	}

	public void search_finding() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		searchbox.clear();
		utilities.sendkeys(searchbox, properties.getProperty("searched_finding"));
		Thread.sleep(3000);

		for (WebElement result : Search_Result_cfd) {
			try {
				if (result.getText().equalsIgnoreCase(properties.getProperty("searched_finding")) ){
					Actions builder = new Actions(driver);
					builder.moveToElement(result).click(result);
					builder.perform();
					Assert.assertTrue(result.getText().equalsIgnoreCase(properties.getProperty("searched_finding")));
					break;
				}
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				try {
					if (result.getText().equalsIgnoreCase(properties.getProperty("searched_finding")) ){
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().equalsIgnoreCase(properties.getProperty("searched_finding")));
						break;
					}
				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (result.getText().equalsIgnoreCase(properties.getProperty("searched_finding"))) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().equalsIgnoreCase(properties.getProperty("searched_finding")));
						break;
					}
				}
			}
		}

	}

	public void verify_added_cfd() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		String popularDiagnosis = properties.getProperty("popular_diagnosis");
		String searchedDiagnosis = properties.getProperty("searched_diagnosis");
		String searchedComplaint = properties.getProperty("searched_complaint");
		String searchedFinding = properties.getProperty("searched_finding");
		
		for (WebElement added_cfd : added_cfd_list) {
			Assert.assertTrue(added_cfd.getText().equalsIgnoreCase(popularDiagnosis)||added_cfd.getText().equalsIgnoreCase(searchedDiagnosis) || added_cfd.getText().equalsIgnoreCase(searchedComplaint)|| added_cfd.getText().equalsIgnoreCase(searchedFinding));

		}
	}

	public void delete_added_diagnosis() {
		utilities.click(delete_diagnosis);
		Assert.assertTrue(deleted_cfd_msg.getText().contains("deleted successfully"));
	}

	public void delete_added_complaint() {
		Actions builder = new Actions(driver);
		builder.moveToElement(delete_complaint).click(delete_complaint).perform();
		Assert.assertTrue(deleted_cfd_msg.getText().contains("deleted successfully"));
	}

	public void delete_added_finding() {
		utilities.click(delete_finding);
		Assert.assertTrue(deleted_cfd_msg.getText().contains("deleted successfully"));
	}

	public void open_prescription_tab() throws InterruptedException {
		for (WebElement tab : tabs) {
			if (tab.getText().equalsIgnoreCase("PRESCRIPTION")) {
				utilities.click(tab);
				Thread.sleep(3000);
			}

		}
	}

	
	public void open_instruction_tab() throws InterruptedException {
//        for (WebElement tabElement : tabs) {
//        	Thread.sleep(5000);
//            if (tabElement.getText().equalsIgnoreCase("INSTRUCTIONS")) {
//            	Thread.sleep(1000);
//            	//utilities.scroll_to_element(tabElement);
//                tabElement.click();
//            }
//        }
		Thread.sleep(2000);

		utilities.scroll_up();
		Thread.sleep(3000);
		Instruction_tab.click();
		
    }

	public void open_summary_tab() throws InterruptedException {
		for (WebElement tabElement : tabs) {
        	Thread.sleep(5000);
            if (tabElement.getText().equalsIgnoreCase("SUMMARY")) {
        		utilities.scroll_up();
        		Thread.sleep(3000);
                tabElement.click();
            }
        }
	}

}
