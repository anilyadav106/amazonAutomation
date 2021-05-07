package com.amazon.automation.testpackage;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.amazon.automation.basepack.Testbase;
import com.amazon.automation.customlisterns.ExtentListeners;
import com.amazon.automation.dataprovider.DataUtil;
import com.amazon.automation.pages.DealsPage;
import com.aventstack.extentreports.Status;

public class SelectDealsByRatingTest extends Testbase {

	private DealsPage dealsPage;
	private WebDriver driver;
	/*
	 * // method to initialize page objects in test class//
	 */
	@BeforeTest
	public void testSetup() {

		try {
			driver=	launchBrowser();
			driver.get(config.getProperty("baseURL"));
			dealsPage = new DealsPage(driver);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterMethodTearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data", groups = { "sanity" })
	public void dealsCategoryTest(Hashtable<String, String> data) {
		String runMode = data.get("Runmode");

		if (runMode.equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the test as runMode equals N");

		} else {
			log.debug("Selecting dealsCategory ");
			ExtentListeners.test.log(Status.INFO, "Selecting dealsCategory for : " + data.get("dealsCategory"));
			dealsPage.selectDealsByRating(data.get("dealsCategory"));
			ExtentListeners.test.log(Status.INFO, "  dealsCategory  has been searched ");
			Assert.assertTrue(dealsPage.dealsCategorySearchText(data.get("dealsCategory")),
					"dealsCategory is not displayed");
			ExtentListeners.test.log(Status.INFO, "dealsCategory results are displayed ");
			log.debug("Required dealsCategory is found ");
		}

	}
}