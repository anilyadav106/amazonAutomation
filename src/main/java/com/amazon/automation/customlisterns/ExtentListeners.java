package com.amazon.automation.customlisterns;

import java.io.IOException;
import java.util.Date;

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

public class ExtentListeners implements ITestListener, ISuiteListener {

	static Date d = new Date();
	// static String fileName = "Extent_" + d.toString().replace(":",
	// "_").replace(" ", "_") + ".html";
	static String fileName = "Extent_Report_noTimeStamp" + ".html";

	public static ExtentReports extent = ExtentManager
			.createReport(System.getProperty("user.dir") + "\\reports\\" + fileName);

	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	public static ExtentTest test;

	public void onTestStart(ITestResult result) {
		System.out.println(" Test started for : " + result.getMethod().getMethodName());

		/* to create a test */
		test = extent.createTest("TestCase : " + result.getMethod().getMethodName());

		/* to add above test to extent report to be generated. */
		testReport.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test passed for  : " + result.getMethod().getMethodName());
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);

		testReport.get().pass(m);

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
		String path = CommonMethods.captureScreeshot();

		try {
			test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {

			e.printStackTrace();
		}
		String logText = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);

		testReport.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test execution skipped for test : " + result.getMethod().getMethodName());

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onFinish(ITestContext context) {

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