package com.demo.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.demo.setup.BaseSelenium;

public class WebUtilities extends BaseSelenium{
	public void sendkeys(WebElement element, String value) {
		element.sendKeys(value);

	}

	public void click(WebElement element) {
		element.click();
	}

	public String getText(WebElement element) {
		return element.getText();
	}
	
	public void moveAndClick(WebElement element) {
		Actions builder = new Actions(driver);
	    builder.moveToElement(element).click(element);
	    builder.perform();
	}

	public static ExtentReports getExtentReport() {
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Automation Results");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Pallavi Patil");
		return extent;
	}
	
	public void scroll_up() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
	}
	
	public void javascript_click(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void scroll_into_view(WebElement element) {
		((JavascriptExecutor) driver).executeScript("argumen"
				+ "ts[0].scrollIntoView(true);", element);
	}
	
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	public void fluent_wait(WebElement element) {
		FluentWait wait = new FluentWait(driver);
		wait.withTimeout(Duration.ofSeconds(5));
		wait.pollingEvery(Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void fluent_wait(List<WebElement> element) {
		FluentWait wait = new FluentWait(driver);
		wait.withTimeout(Duration.ofSeconds(5));
		wait.pollingEvery(Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public void explicitwait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void explicitwait(List<WebElement> element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public int get_current_time() {
		Date currentTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh");
        String formattedTime = format.format(currentTime);
		int current_time=0;
		
		try {
			current_time = Integer.parseInt(formattedTime);
			System.out.println("Current date and time is " + current_time);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return current_time;
	}
	
	public String takeScreenshot(String testName) throws IOException{
		
		File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"/screenshots/"+testName+".png";
		FileUtils.copyFile(SourceFile,new File(destinationFilePath));
		return destinationFilePath;
	}
	
	public void verify_equals(String actual, String expected) {
			softAssert.assertEquals(actual, expected);
	}
	
	public void verify_true(Boolean condition) {
		softAssert.assertTrue(condition);
	}
	
	public boolean isTestRunnable(String testName, ExcelReader excel) {
		int rows = excel.getRowCount(testName);

		for(int rNo = 2; rNo <= rows; rNo++)
		{
			String runmode = excel.getCellData(testName, "Run_Mode", rNo );
			System.out.println(runmode);
				if(runmode.equalsIgnoreCase("Y"))
					return true;
		}
		return false;
	}
}
