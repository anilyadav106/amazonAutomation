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

/*coupons > shop by category > select category*/
public class couponsCategory extends testbase {

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