package com.amazon.automation.testpackage;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.automation.basepack.Testbase;
import com.amazon.automation.customlisterns.ExtentListeners;
import com.amazon.automation.dataprovider.DataUtil;
import com.amazon.automation.pages.HomePage;
import com.aventstack.extentreports.Status;

public class AddToCartTest extends Testbase {

	private HomePage home;

	/*
	 * / This is method to initialize page objects in test class/
	 */

	@BeforeMethod
	public void testSetup() {

		try {
			launchBrowser();
			driver.get(config.getProperty("baseURL"));
			home = new HomePage(driver);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void afterMethodTearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data", groups = ("integration"))
	public void productAddtoCartTest(Hashtable<String, String> data) {
		String runMode = data.get("Runmode");

		if (runMode.equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the test as runMode equals N");

		} else {
			log.debug("Adding the product to cart");
			ExtentListeners.test.log(Status.INFO, "Add to product test started for :" + data.get("productName"));

			home.addtoCartAProduct(data.get("productName"));

			Assert.assertTrue(home.getAddedToCartSuccessMessage().isDisplayed(),
					"Product is not added to cart successfuly");
			ExtentListeners.test.log(Status.INFO, "Product added to cart  ");
			log.debug("Product added to cart successfully.");
		}

	}
}