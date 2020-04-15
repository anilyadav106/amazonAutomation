package com.amazonAutomationFramework.customlisterns;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports createReport(String fileName) {

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		ExtentReports extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Anil Kumar");
		extent.setSystemInfo("Organization", "Nagarro");
		extent.setSystemInfo("Build no", "NAGARRO-1234");

		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Extent report Page Title");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Test Report for Amazon Testing Framework designed by Anil");

		return extent; // in the end returns constructed extent report
	}

}
