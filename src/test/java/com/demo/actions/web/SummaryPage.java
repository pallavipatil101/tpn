package com.demo.actions.web;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.demo.setup.BaseSelenium;
import com.demo.testcases.web.WebLoginTest;
import com.demo.utilities.ExcelUtils;
import com.demo.utilities.WebUtilities;

public class SummaryPage extends BaseSelenium {
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();
	Logger logger = Logger.getLogger(WebLoginTest.class);

	public SummaryPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//p[@class = 'ng-binding' and text() = 'Diagnosis']")
	private WebElement Diagnosis_Card;

	@FindBy(xpath = "//*[@id=\"print\"]/div/table[1]/tbody/tr[2]/td/div/ul/li[2]/div/h6")
	private List<WebElement> Diagnosis_List;
	
	@FindBy(xpath = "//p[@class = 'ng-binding' and text() = 'Complaints and Findings']")
	private WebElement Complaints_FindingsCard;
	
	@FindBy(xpath = "//*[@id=\"print\"]/div/table[1]/tbody/tr[2]/td/div/ul/li[1]/div/h6")
	private List<WebElement> Complaints_Findings_List;
	
	@FindBy(xpath = "//p[@class = 'ng-binding' and text() = 'Instructions']")
	private WebElement Instructions_Card;
	
	@FindBy(xpath = "//*[@id=\"print\"]/div/table[1]/tbody/tr[4]/td/div/ul/li[1]/div/h6")
	private List<WebElement> Instructions_List;
	
	@FindBy(xpath = "//p[@class = 'ng-binding' and text() = 'Tests']")
	private WebElement Tests_Card;
	
	@FindBy(xpath = "//*[@ng-if= 'test.name']")
	private List<WebElement> Tests_List;
	
	@FindBy(xpath = "//p[@class = 'ng-binding' and text() = 'Procedures']")
	private WebElement Procedures_Card;
	
	@FindBy(xpath = "//*[@ng-if= 'procedure.name']")
	private List<WebElement> Procedures_List;
	
	@FindBy(xpath = "//table[@class=\"table-prescription\"]//tr/th")
	private List<WebElement> Drug_Table_Headers;
	
	@FindBy(xpath = "//table[@class=\"table-prescription\"]/tbody/tr")
	private List<WebElement> Drugs_Rows;
	
	@FindBy(xpath = "//*[@id='print']/div/table[1]/tbody/tr[3]/td/div/table/tbody/tr/td/div/div/p")
	private List<WebElement> Drugs_Names_List;
	
	@FindBy(xpath = "//table[@class = 'table-fixed']/thead/tr/th")
	private List<WebElement> Drug_Table_Headings;
	
	@FindBy(xpath = "//table[@class = 'table-prescription']/tbody/tr")
	private List<WebElement> Drug_Frequency_Checkmarks_rows;
	
	@FindBy(css = ".icon-check.ng-scope")
	private List<WebElement> Drug_Frequency_Checkmarks;
	
	@FindBy(xpath = "//table[@class='table-fixed']/tbody/tr[1]/td[7]/h6/span")
	private List<WebElement> Duration_Column;
	
	@FindBy(xpath = "//div[@class = 'btn-dashboard']/a[1]")
	private WebElement Save_Button;
	
	@FindBy(xpath = "//div[@class='btn-dashboard']/a[2]")
	private WebElement Save_Print_Button;
	
	@FindBy(xpath = "//div[@class = 'btn-wrap'][1]")
	private WebElement Records_Button;
	
	@FindBy(xpath = "//*[@heading = 'Records and Images']")
	private WebElement Records_n_Images_Button;

	@FindBy(xpath = "//center[@class = 'ng-binding ng-scope']")
	private List<WebElement> Records_Date;
	
	@FindBy(xpath = "//button[@class= 'btn btn-blue btn-back']")
	private WebElement Records_Back_Button;
	
	@FindBy(css = ".nav.nav-tabs>li")
	private List<WebElement> tabs;
	
	@FindBy(xpath = "//li/a[text()=\"SUMMARY\"]")
	private WebElement summary;
	
	public void verify_diagnosis_in_summary(String popularDiagnosis, String searchedDiagnosis) throws Exception {
		for(int i=0; i<Diagnosis_List.size(); i++)
			{
				if(i == 0) {
					utilities.verify_true(Complaints_Findings_List.get(i).getText().equals(popularDiagnosis));

				}
				else if(i == 1) {
					utilities.verify_true(Complaints_Findings_List.get(i).getText().equals(searchedDiagnosis));
				}
			}
	}
	
	public void verify_complaint_finding_in_summary(String searchedComplaint, String searchedFinding) throws Exception {
		for(int i=0; i<Complaints_Findings_List.size(); i++)
		{
			if(i == 0) {
				utilities.verify_true(Complaints_Findings_List.get(i).getText().equals(searchedComplaint));

			}
			else if(i == 1) {
				utilities.verify_true(Complaints_Findings_List.get(i).getText().equals(searchedFinding));
			}
		}
	}
	
	public void verify_instructions_in_summary(String searchedInstruction, String createdInstruction) throws Exception {
		for(int i=0; i<Instructions_List.size(); i++)
		{
			if(i == 0)
			{
				System.out.println("----Instructions_List"+Instructions_List.get(i).getText());
				utilities.verify_true(Instructions_List.get(i).getText().equalsIgnoreCase(searchedInstruction));
			}
			else if(i == 1)
			{
				System.out.println("----Instructions_List"+Instructions_List.get(i).getText());
				utilities.verify_true(Instructions_List.get(i).getText().equalsIgnoreCase(createdInstruction));	
			}
		}
	}
	
	public void verify_tests_in_summary(String searchedTest, String createdTest) throws Exception {
		Thread.sleep(5000);
		for(int i=0; i<Tests_List.size(); i++)
		{
			if(i == 0)
			{
				Thread.sleep(3000);
				System.out.println("----test"+Tests_List.get(i).getText());
				utilities.verify_true(Tests_List.get(i).getText().equalsIgnoreCase(searchedTest));
			}
			else if(i == 1)
			{
				Thread.sleep(3000);
				System.out.println("----test"+Tests_List.get(i).getText());
				utilities.verify_true(Tests_List.get(i).getText().equalsIgnoreCase(createdTest));	
			}
		}
	}
	
	public void verify_procedures_in_summary(String searchedProcedure , String createdProcedure) throws Exception {

		for(int i=0; i<Procedures_List.size(); i++)
		{
			if(i == 0)
			{
				System.out.println("----Procedures_List"+Procedures_List.get(i).getText());
				utilities.verify_true(Procedures_List.get(i).getText().equalsIgnoreCase(searchedProcedure));
			}
			else if(i == 1)
			{
				System.out.println("----Procedures_List"+Procedures_List.get(i).getText());
				utilities.verify_true(Procedures_List.get(i).getText().equalsIgnoreCase(createdProcedure));	
			}
		}
	}
	
	public void verify_drugs_in_summary(String popularDrug, String searchedDrug) throws Exception {
		for(int i=0; i<Drugs_Names_List.size(); i++)
		{
			if(i == 0)
			{
				System.out.println("----"+Drugs_Names_List.get(i).getText());
				utilities.verify_true(Drugs_Names_List.get(i).getText().equalsIgnoreCase(popularDrug));
			}
			else if(i == 1)
			{
				System.out.println("----"+Drugs_Names_List.get(i).getText());
				utilities.verify_true(Drugs_Names_List.get(i).getText().equalsIgnoreCase(searchedDrug));	
			}
			else
			{
				logger.info("Popular and searched drugs are either not added or not matching");
			}
		}
	}
	
	public void verify_created_drug_in_summary(String createdDrug) {
		for(int i=0; i<Drugs_Names_List.size(); i++)
		{
			if(i == 2)
			{
				System.out.println("----"+Drugs_Names_List.get(i).getText());
				utilities.verify_true(Drugs_Names_List.get(i).getText().equalsIgnoreCase(createdDrug));
			}
			
			else
			{
				logger.info("Created drug is either not added or not matching");
			}
		}
	}
	
	public void verify_drug_frequency(String popularDrugFrequency,String searchedDrugFrequency, String newDrugFrequency ) throws Exception {
		for(int i = 0; i<Duration_Column.size(); i++)
		{
			if(i == 0)
			{
				utilities.verify_equals(popularDrugFrequency, Duration_Column.get(i).getText());
			}
			if(i == 1)
			{
				utilities.verify_equals(searchedDrugFrequency, Duration_Column.get(i).getText());
			}
			if(i == 2)
			{
				utilities.verify_equals(newDrugFrequency, Duration_Column.get(i).getText());
			}
		}
	}
	
	public void verify_drug_timings(String frequency_M, String frequency_A, String frequency_E, String frequency_N ) {
				
				for(int x = 1; x <= 3; x++)
				{
					List<WebElement> checks = driver.findElements(By.xpath("//*[@id='print']/div/table/tbody/tr[3]/td/div/table/tbody/tr["+x+"]/td[2]/table/tbody/tr/td/span/i"));
					if(x == 1)
					{
						for(int j = 0; j < checks.size(); j++)
						{
							if(j == 0)
							{					
								utilities.verify_true(checks.get(j).getAttribute("ng-if").contains(frequency_M));
							}
							if(j == 1)
							{
								utilities.verify_true(checks.get(j).getAttribute("ng-if").contains(frequency_N));
							}
						}
					
					}
					
					if(x == 2)
					{
						for(int j = 0; j < checks.size(); j++)
						{if(j == 0)
						{					
							utilities.verify_true(checks.get(j).getAttribute("ng-if").contains(frequency_M));
						}
						if(j == 1)
						{
							utilities.verify_true(checks.get(j).getAttribute("ng-if").contains(frequency_A));
						}
						if(j == 2)
						{
							utilities.verify_true(checks.get(j).getAttribute("ng-if").contains(frequency_E));
						}
						}
					
					}

				}
		
	}
	
	public void verify_record_is_saved(String recordDate) throws Exception {
		utilities.click(Save_Button);
		utilities.scroll_into_view(Records_Button);
		Thread.sleep(3000);
		utilities.click(Records_Button);
		Thread.sleep(4000);
		utilities.click(Records_n_Images_Button);
		
		for(WebElement date : Records_Date)
		{
			utilities.verify_equals(date.getText(), recordDate);
			System.out.println("########################### DATE: "+date.getText());
			System.out.println("########################### EXCEL DATE: "+recordDate);

		}

	}
	
	public void verify_record_can_be_printed() throws InterruptedException {
		//Navigating Back to CFD page
		Thread.sleep(4000);
		utilities.click(Records_Back_Button);
		Thread.sleep(4000);
		
		//Navigating Back to summary page		
		utilities.click(summary);
		System.out.println(">>Class Name: "+summary.getAttribute("class"));
		utilities.verify_true(summary.getAttribute("class").equalsIgnoreCase("active"));
		Thread.sleep(4000);
		try {
			utilities.click(Save_Print_Button);
		}
		catch(org.openqa.selenium.StaleElementReferenceException e) {
			utilities.click(Save_Print_Button);

		}
		String parent=driver.getWindowHandle();
		System.out.println(">>parent: "+parent);
	
		String childwindow = null;
		Set<String> windowids = driver.getWindowHandles();
		Iterator<String> iterator = windowids.iterator();
		
		while(iterator.hasNext())
		{
			if(parent != iterator.next()) {
				childwindow = iterator.next();
				System.out.println(">>Child window"+childwindow);
			}
			
		}
		
	
		
		driver.switchTo().window(childwindow);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>.Child window titile"+driver.getTitle());
		utilities.verify_true(driver.getTitle().contains("prescription"));
		driver.close();
	}
	
	
}
