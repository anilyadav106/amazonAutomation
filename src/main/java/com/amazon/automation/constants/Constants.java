package com.amazon.automation.constants;

import java.io.File;
import java.util.Date;

public class Constants {

	private static Date d = new Date();
	private static String fileName = d.toString().replace(":", "_").replace(" ", "_");
	private static String destPath = System.getProperty("user.dir");
	public static File destination = new File(destPath + "/FailedTestCasesScreenshots/" + fileName + ".jpeg");

}
