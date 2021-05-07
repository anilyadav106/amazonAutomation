package com.amazon.automation.customlisterns;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.amazon.automation.commonmethods.CommonMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.tesults.tesults.Results;

public class ExtentListeners implements ITestListener, ISuiteListener {
	List<Map<String, Object>> testCases = new ArrayList<Map<String, Object>>();
	static Date d = new Date();
	// static String fileName = "Extent_" + d.toString().replace(":",
	// "_").replace(" ", "_") + ".html";
	static String fileName = "Extent_Report_noTimeStamp" + ".html";

	public static ExtentReports extent = ExtentManager
			.createReport(System.getProperty("user.dir") + "\\reports\\" + fileName);
/*To make our report thread safes*/
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	public static ExtentTest test;

	public void onTestStart(ITestResult result) {
		System.out.println(" Test started for : " + result.getMethod().getMethodName());

		/* to create a test */
		test = extent.createTest("TestCase : " + result.getMethod().getMethodName());

		/* to add above test objects to threadlocal, so that correct test object is assigned in case of parallel executions. */
		testReport.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test passed for  : " + result.getMethod().getMethodName());
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);

		testReport.get().pass(m);
		/* to add in tseults report */

		Map<String, Object> passTestCase = new HashMap<String, Object>();
		passTestCase.put("name", result.getMethod().getMethodName());
		passTestCase.put("result", "pass");
		testCases.add(passTestCase);

	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test failed for : " + result.getMethod().getMethodName());

		String exceptionMessage = result.getThrowable().getMessage();

		testReport.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>"
						+ "Exception Occured: Click here to see" + "</font>" + "</b >" + "</summary>"
						+ exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");
		/*
		 * to capture screen shot also for test failure and add to test report
		 */
		String path = CommonMethods.captureScreeshot(result.getMethod().getMethodName());

		try {
			test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {

			e.printStackTrace();
		}
		String logText = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);

		testReport.get().log(Status.FAIL, m);
		/* to add in tseults report */

		Map<String, Object> failTestCase = new HashMap<String, Object>();
		failTestCase.put("name", result.getMethod().getMethodName());
		failTestCase.put("result", "fail");

		List<String> files = new ArrayList<String>();
		files.add(path); // add screen shot file path

		failTestCase.put("files", files);

		testCases.add(failTestCase);

	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test execution skipped for test : " + result.getMethod().getMethodName());

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);
		/* to add in tseults report */

		Map<String, Object> skipTestCase = new HashMap<String, Object>();
		skipTestCase.put("name", result.getMethod().getMethodName());
		skipTestCase.put("result", "unknown");
		testCases.add(skipTestCase);

	}

	public void onFinish(ITestContext context) {
		/* to add in tseults report */

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("target",
				"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6ImI0NjYyNjM4LWVlZTktNGRmNy05NDhlLTdjZGI3YWJmZDZhNi0xNTg4MjYzMDYxNzIxIiwiZXhwIjo0MTAyNDQ0ODAwMDAwLCJ2ZXIiOiIwIiwic2VzIjoiZDM5OWQwOWItOTNhMS00NWRlLTgzMWMtMzdkNDk0NGQ5YWMzIiwidHlwZSI6InQifQ.wBz2yKeQXIHReSY7s9v7Ujw4M9gt6FhqdPRdoAq9MX0");

		Map<String, Object> results = new HashMap<String, Object>();
		results.put("cases", testCases);
		data.put("results", results);

		Map<String, Object> response = Results.upload(data);
		System.out.println("success: " + response.get("success"));
		System.out.println("message: " + response.get("message"));
		System.out.println("warnings: " + ((List<String>) response.get("warnings")).size());
		System.out.println("errors: " + ((List<String>) response.get("errors")).size());

		/* to flush the extent report */
		if (extent != null) {

			extent.flush();
		}

	}

	public void onStart(ISuite suite) {
		Reporter.log("test suite started");

	}

	public void onFinish(ISuite suite) {

		Reporter.log("test suite ended");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
