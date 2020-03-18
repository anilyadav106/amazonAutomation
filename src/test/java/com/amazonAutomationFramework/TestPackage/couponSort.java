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
import com.amazonAutomationFramework.pages.couponsPage;
import com.aventstack.extentreports.Status;

import DataProvider.DataUtil;

public class couponSort extends testbase {

	couponsPage couponspage;

	/* method to initialize page objects in test class */

	@BeforeTest
	public void testSetup() {

		try {
			launchbrowser();
			driver.get(config.getProperty("baseURL"));
			couponspage = new couponsPage(driver);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterMethodTearDown() {

		driver.close();
	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void couponSortTest(Hashtable<String, String> data) {

		String runMode = data.get("Runmode");

		if (runMode.equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the test as runMode equals N");

		} else {
			System.out.println("Inside Test method...");
			ExtentListeners.test.log(Status.INFO, "Coupon sort test started for :" + data.get("sortText"));
			String sortText = data.get("sortText");

			couponspage.couponsSort(sortText);

			Assert.assertTrue(couponspage.isAllCouponsTextDisplayed(),
					"All coupons text is not displayed , means user is not on copouns sort page.");
			ExtentListeners.test.log(Status.INFO, "Coupon sorted successfully");
		}
	}
}
