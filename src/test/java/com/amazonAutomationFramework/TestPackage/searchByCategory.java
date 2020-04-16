package com.amazonAutomationFramework.TestPackage;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.amazonAutomationFramework.basePack.testbase;
import com.amazonAutomationFramework.customlisterns.ExtentListeners;
import com.amazonAutomationFramework.pages.homePage;
import com.aventstack.extentreports.Status;

import DataProvider.DataUtil;

public class searchByCategory extends testbase {

	homePage home;

	/* method to initialize page objects in test class */

	@BeforeTest
	public void testSetup() {

		try {
			launchbrowser();
			driver.get(config.getProperty("baseURL"));
			home = new homePage(driver);

		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterMethodTearDown() {

		driver.close();
	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data", groups = ("sanity"))
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