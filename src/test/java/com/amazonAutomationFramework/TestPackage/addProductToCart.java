package com.amazonAutomationFramework.TestPackage;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazonAutomationFramework.basePack.testbase;
import com.amazonAutomationFramework.customlisterns.ExtentListeners;
import com.amazonAutomationFramework.pages.homePage;
import com.aventstack.extentreports.Status;

import DataProvider.DataUtil;

public class addProductToCart extends testbase {

	public homePage home;

	/* method to initialize page objects in test class */

	@BeforeMethod
	public void testSetup() {

		try {
			launchbrowser();
			driver.get(config.getProperty("baseURL"));
			home = new homePage(driver);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void afterMethodTearDown() {

		driver.quit();
	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data", groups = ("sanity"))
	public void productAddtoCartTest(Hashtable<String, String> data) {
		String runMode = data.get("Runmode");

		if (runMode.equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the test as runMode equals N");

		} else {

			ExtentListeners.test.log(Status.INFO, "Add to product test started for :" + data.get("productName"));

			home.addtoCartAProduct(data.get("productName"));

			Assert.assertTrue(home.getAddedToCartSuccessMessage().isDisplayed(),
					"Product is not added to cart successfuly");
			ExtentListeners.test.log(Status.INFO, "Product added to cart  ");
		}

	}
}
