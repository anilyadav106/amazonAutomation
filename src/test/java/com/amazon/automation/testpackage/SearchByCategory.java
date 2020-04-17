package com.amazon.automation.testpackage;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.amazon.automation.basepack.Testbase;
import com.amazon.automation.customlisterns.ExtentListeners;
import com.amazon.automation.dataprovider.DataUtil;
import com.amazon.automation.pages.HomePage;
import com.aventstack.extentreports.Status;

public class SearchByCategory extends Testbase {

	private HomePage home;

	/* method to initialize page objects in test class */
	@BeforeTest
	public void testSetup() {

		try {
			launchBrowser();
			driver.get(config.getProperty("baseURL"));
			home = new HomePage(driver);

		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterMethodTearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data", groups = ("integration"))
	public void categorySelectTest(Hashtable<String, String> data) throws InterruptedException {
		String runMode = data.get("Runmode");

		if (runMode.equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the test as runMode equals N");

		} else {
			log.debug("Product search category started ");
			ExtentListeners.test.log(Status.INFO, " Product search category started for :" + data.get("categoryName"));
			home.selectCategory(data.get("categoryName"));
			ExtentListeners.test.log(Status.INFO, data.get("categoryName") + "  selected succesfully");
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, home.getExpectedTitle(), "Title mismatch");
			ExtentListeners.test.log(Status.INFO, "Product of the category selected searched successfully");
			log.debug("Product of the category selected searched successfully");
		}

	}
}