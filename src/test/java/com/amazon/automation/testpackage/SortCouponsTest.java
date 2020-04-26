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
import com.amazon.automation.pages.CouponsPage;
import com.aventstack.extentreports.Status;

public class SortCouponsTest extends Testbase {

	private CouponsPage couponspage;

	/*
	 * method to initialize page objects in test class / /
	 */
	@BeforeTest
	public void testSetup() {

		try {
			launchBrowser();
			driver.get(config.getProperty("baseURL"));
			couponspage = new CouponsPage(driver);

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
	public void couponSortTest(Hashtable<String, String> data) {

		String runMode = data.get("Runmode");

		if (runMode.equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the test as runMode equals N");

		} else {
			log.debug("Coupon sort test started ");
			ExtentListeners.test.log(Status.INFO, "Coupon sort test started for :" + data.get("sortText"));
			String sortText = data.get("sortText");

			couponspage.couponsSort(sortText);

			Assert.assertTrue(couponspage.isAllCouponsTextDisplayed(),
					"All coupons text is not displayed , means user is not on copouns sort page.");
			ExtentListeners.test.log(Status.INFO, "Coupon sorted successfully");
			log.debug("Coupon sorted successfully ");
		}
	}
}