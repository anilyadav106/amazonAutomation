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

//coupons>shop by category>select category//

public class SelectCouponsCategoryTest extends Testbase {

	private CouponsPage couponspage;

	/*
	 * // method to initialize page objects in test class//
	 */
	@BeforeTest
	public void testSetup() {

		try {
			launchBrowser();
			driver.get(config.getProperty("baseURL"));
			couponspage = new CouponsPage(driver);

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

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data", groups = { "sanity" })
	public void couponCategoryTest(Hashtable<String, String> data) {
		String runMode = data.get("Runmode");

		if (runMode.equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the test as runMode equals N");

		} else {
			log.debug("Searching coupon category ");
			ExtentListeners.test.log(Status.INFO, "Searching coupon category for : " + data.get("categoryText"));
			couponspage.selectCouponsCategory(data.get("categoryText"));
			ExtentListeners.test.log(Status.INFO, "  coupon category  has been searched ");
			Assert.assertEquals(driver.getTitle(), couponspage.getWatchesPageTitle(), "Watches title is not matched");
			ExtentListeners.test.log(Status.INFO, "Required coupon category is found ");
			log.debug("Required coupon category is found ");
		}

	}
}