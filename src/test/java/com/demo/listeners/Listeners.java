package com.demo.listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class Listeners extends BaseSelenium implements ITestListener,IRetryAnalyzer {
	ExtentReports extentReport = ExtentReporter.getExtentReport();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	public WebDriver driver = null;
	WebUtilities cu = new WebUtilities();
	int retryAttemptsCounter = 0;
	int maxRetryLimit = 3;
	
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName()+" -- This Testexecution started");
		extentTestThread.set(extentTest);
//		if(!cu.isTestRunnable(result.getName(), excel));
//		{
//			throw new SkipException("Skipping the test"+result.getName()+ "as excel sheet mentions not to run it.");
//		}
	}

	public void onTestSuccess(ITestResult result) {
		String testname = result.getName();
		//extentTest.log(Status.PASS,testname+" Test Passed");
		extentTestThread.get().log(Status.PASS,testname+" -- This test Passed. The test description is given below: "+result.getMethod().getDescription());	// making it thread safe
	}

	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {
			if(retryAttemptsCounter < maxRetryLimit){
				retryAttemptsCounter++;
				return true;
			}
		}
		return false;
	}
	
	public void onTestFailure(ITestResult result) {
		//extentTest.fail(result.getThrowable());	// to print cause of error/exception in report. without it itll show as pass
		String testname = result.getName();
		extentTestThread.get().log(Status.FAIL,testname+" -- This test has Failed! The test description is given below: "+result.getMethod().getDescription());
		//extentTestThread.get().log(null, result.getMethod().getDescription()+"This is description");
		extentTestThread.get().fail(result.getThrowable());
		
		try {
			String screenshotPath = cu.takeScreenshot(testname);
			extentTestThread.get().addScreenCaptureFromPath(screenshotPath, testname);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		String testname = result.getName();
		extentTestThread.get().log(Status.SKIP,testname+" -- This test has been skipped. Test description: "+result.getMethod().getDescription());
	}

	
	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
	}


}
