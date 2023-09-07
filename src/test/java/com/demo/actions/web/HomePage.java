package com.demo.actions.web;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.setup.BaseSelenium;
import com.demo.testcases.web.WebLoginTest;
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
	
	@FindBy(xpath = "//md-select[@ng-model='appointment.user.child.gender']")
	private WebElement gender_dropdown_btn;
	
	@FindBy(xpath = "//*[@ng-repeat = 'gender in genderList']")
	private List<WebElement> gender_dropdown_options;
	
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
				System.out.println(">>>>>" + slot.getAttribute("class"));
				utilities.javascript_click(slot_rect);
				break;
			}
		}
	}

	public void enter_mobile_no(String mobile) throws Exception {
		utilities.sendkeys(enter_mobile, mobile);

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
	
	public void enter_patient_name(String name) throws IOException {
		utilities.sendkeys(enter_name, name);

	}
	
	public void enter_patient_age(String age) throws IOException {
		utilities.sendkeys(enter_age_years, age);

	}
	
	public void select_patient_gender(String patient_gender) throws IOException, InterruptedException {
		utilities.click(gender_dropdown_btn);
		Thread.sleep(4000);
		for(WebElement gender : gender_dropdown_options)
		{
			if(gender.getText().equalsIgnoreCase(patient_gender))
			{
				Thread.sleep(4000);
				utilities.scroll_into_view(gender);
				utilities.explicitwait(gender);
				utilities.click(gender);
				utilities.verify_equals(gender.getText(), properties.getProperty("patient_gender"));
			}
		}
	}
	
	public void click_on_Add_Appointment_Button() {
		utilities.click(Add_Appointment_Btn);
	}

	public void verify_diabled_slot() throws ParseException {
		for (WebElement slot : All_slots_cards) {
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
					utilities.verify_true(slot.getAttribute("class").contains("disabled"));
				}
			}
			break;
		}
	}

	public void verify_booked_slot(String patient_name) throws InterruptedException, IOException {
		 List<WebElement> cards = driver.findElements(By.xpath("//div[@class=\"panel-body ng-scope\"]"));
	        
		 for (int i = 2; i < cards.size(); i++) 
		 {
	         
			 Thread.sleep(2000);
	         WebElement card = driver.findElement(By.xpath("(//div[contains(@class,\"panel-body\")])[" + i + "]"));   			
//   			 if(card.getText().contains(patient_name)) {
//   			  }
	         utilities.verify_true(card.getText().contains(patient_name));
   			
	     }
	}
	


	public void select_created_appointment(String patient_name) throws InterruptedException, IOException {		
		 List<WebElement> cards = driver.findElements(By.xpath("//div[@class=\"panel-body ng-scope\"]"));
	        int cardlength = cards.size();
	        for (int i = 2; i < cardlength; i++) {
	          Thread.sleep(2000);
	          WebElement card = driver.findElement(By.xpath("(//div[contains(@class,\"panel-body\")])[" + i + "]"));
	          System.out.println("???????????PATIENT NAMES:"+card.getText());
      			System.out.println("!!!!!!!!!!PATIENT NAME TXT FILE: "+patient_name);
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
