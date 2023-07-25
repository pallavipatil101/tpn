package com.demo.actions.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.demo.setup.BaseSelenium;
import com.demo.testcases.web.WebLoginTest;
import com.demo.utilities.ExcelUtils;
import com.demo.utilities.WebUtilities;

public class HomePage extends BaseSelenium {
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();
	Logger logger = Logger.getLogger(WebLoginTest.class);

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[@id=\"content\"]/div/div/div[1]/div[1]/div[3]/div/div/div/select")
	private WebElement drop;

	@FindBy(css = ".panel.panel-default.panel-appointment.flex-gt-md-auto.flex-100")
	private List<WebElement> All_slots_cards;

	@FindBy(xpath = "//*[@class= 'panel-title ng-binding']")
	private List<WebElement> Slots_Time;

	@FindBy(xpath = "//*[@id=\"content\"]/div/div/div[2]/div[2]/md-grid-list/md-grid-tile/figure/div[2]")
	private List<WebElement> slots;

	@FindBy(xpath = "//div[contains(@class, 'panel-body ng-scope')]")
	private List<WebElement> rectangleList;

	@FindBy(xpath = "//*[@class=\"btn-wrap ng-scope\"]/a[3]")
	private List<WebElement> delete_appointment_buttons;

	@FindBy(name = "mobile")
	private WebElement enter_mobile;

	@FindBy(xpath = "//*[@class=\"md-autocomplete-suggestions\"]/li")
	private List<WebElement> patient_list;

	@FindBy(xpath = "//*[@class=\"md-autocomplete-suggestions\"]/li[1]")
	private WebElement single_patient;

	@FindBy(css = ".flex-25.btn-save.md-button.ng-scope.md-ink-ripple")
	private WebElement Add_Appointment_Btn;

	@FindBy(css = ".field-wrap.field-name.ng-binding")
	private List<WebElement> added_cfd_list;

	@FindBy(name = "child_name")
	private WebElement enter_name;
	
	@FindBy(name = "year")
	private WebElement enter_age_years;
	
	@FindBy(xpath = "//div[@class = 'app_box text-center']/p[1]")
	private List<WebElement> created_appointment_patient_names;
	
	public void select_evening_time() throws InterruptedException {
		Select s = new Select(drop);
		s.selectByVisibleText("Evening");
		Thread.sleep(3000);
	}

	public void select_a_slot() {

		for (WebElement slot : All_slots_cards) {

			if ((slot.getAttribute("class")).contains("disabled")
					|| (slot.getAttribute("class")).contains("non-members")) {
			}

			else {
				WebElement slot_rect = slot.findElement(By.cssSelector(".panel-body.ng-scope"));
				// WebElement slot_rect = slot.findElements(rectangle);
				System.out.println(">>>>>" + slot.getAttribute("class"));
				utilities.scroll_to_element(slot_rect);
				break;
			}
		}
		

//		List<WebElement> list = driver.findElements(By.xpath("//div[contains(@class, 'panel-body ng-scope')]\n"
//				+ "\n"
//				+ ""));
//
//        int length = list.size();
//        System.out.println("VALUE OF length: "+length);
//
//        for (int i = 0; i < length; i++) {
//            System.out.println("VALUE OF i: "+i);
//            WebElement card= driver.findElement(By.xpath("(//div[contains(@class,'panel-body')])["+i+"]"));
//            utilities.fluent_wait(card);
//            card.click();
//            driver.navigate().back();
//            utilities.implicitWait();
//            System.out.println("click done");
//        }
				
	}

	public void enter_mobile_no() throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		utilities.sendkeys(enter_mobile, properties.getProperty("mobile_number"));
	}

	public void choose_patient() {
		for (WebElement patient : patient_list) {
			try {
				utilities.click(patient);
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				utilities.click(single_patient);
			}
		}

	}
	
	public void enter_patient_name() throws IOException {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		utilities.sendkeys(enter_name, properties.getProperty("patient_name"));
	}
	
	public void enter_patient_age() throws IOException {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		utilities.sendkeys(enter_age_years, properties.getProperty("patient_age"));
	}
	
	public void click_on_Add_Appointment_Button() {
		utilities.click(Add_Appointment_Btn);
	}

	public void verify_diabled_slot() throws ParseException {
		for (WebElement slot : All_slots_cards) {
			// Create object of SimpleDateFormat class and decide the format
			int current_time = utilities.get_current_time();
			
			for (int i = 1; i < Slots_Time.size(); i++) 
			{
				String cardTime = Slots_Time.get(i).getText();
				
				String verify_time = cardTime.substring(9).toLowerCase();
				
				String sub_time1 = verify_time.substring(0, 1);
				logger.info("Slot Time: "+sub_time1);
				int slot_timing = 0; 
				
				try {
					slot_timing = Integer.parseInt(sub_time1);
				}
				catch(NumberFormatException e) {
					e.printStackTrace();
				}
				
								
				if(current_time>slot_timing)
				{
					Assert.assertTrue(slot.getAttribute("class").contains("disabled"));
				}

			}
			break;
		}
	}

	public void verify_booked_slot() {
		for (WebElement slot : All_slots_cards) {
			if (slot.getAttribute("class").contains("non-members")) {
				logger.info("Appoibtment created successfully.");
			}
			else {
				System.out.println("Appointment creation failed.");
			}
			//Assert.assertTrue(slot.getAttribute("class").contains("non-members"));
	
		}
	}
	


	public void select_created_appointment() throws InterruptedException, IOException {
		
//		for(WebElement name : created_appointment_patient_names)
//        {
//    		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
//    		if(name.getText().equals(properties.getProperty("patient_name")));
//    		{
//				WebElement slot_rect = driver.findElement(By.cssSelector(".panel-body.ng-scope"));
//				slot_rect.click();
//    		}
//        }
		
		
//		for (WebElement slot : All_slots_cards) {
//			if ((slot.getAttribute("class")).contains("non-members")) {
//				WebElement slot_rect = slot.findElement(By.cssSelector(".panel-body.ng-scope"));
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//				js.executeScript("arguments[0].click();", slot_rect);
//				for(WebElement addedcfd : added_cfd_list)
//				{
//					if(addedcfd.isDisplayed())
//					{
//						driver.navigate().back();
//					}
//				}
//			}
//
//			else {
//
//			}
//		}
		
		
		 List<WebElement> cards = driver.findElements(By.xpath("//div[@class=\"panel-body ng-scope\"]"));
	        int cardlength = cards.size();
	        for (int i = 2; i < cardlength; i++) {
	          Thread.sleep(2000);
	          WebElement card = driver.findElement(By.xpath("(//div[contains(@class,\"panel-body\")])[" + i + "]"));
	           properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
      			String patient_name = properties.getProperty("patient_name");
      			if(card.getText().contains(patient_name)) {
      				Thread.sleep(3000);
      				card.click();
      				break;
      			}
      			else {
      				logger.info("Matching patint not found to click on card.");
      			}
	        }
	           

	        
	}

	
}
