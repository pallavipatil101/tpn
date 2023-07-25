package com.demo.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class Listeners extends BaseSelenium implements ITestListener {
	ExtentTest test;
	ExtentReports extent = WebUtilities.getExtentReport();

	public void onTestStart(ITestResult result) {
		System.out.println("Test started: " + result.getMethod().getMethodName());

		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test passed: " + result.getMethod().getMethodName());
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {

		test.fail(result.getThrowable());
//		screenshot();
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test skipped: " + result.getMethod().getMethodName());
	}

	public void onFinish(ITestContext context) {
		System.out.println("All tests finished!");
		extent.flush();

	}

}
