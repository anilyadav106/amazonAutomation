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
import com.amazonAutomationFramework.pages.loginPage;
import com.aventstack.extentreports.Status;

import DataProvider.DataUtil;

public class amazonLoginValidUser extends testbase {

	loginPage login;

	@BeforeTest
	public void beforeMethodSetup() {

		try {
			launchbrowser();
			driver.get(config.getProperty("baseURL"));
			login = new loginPage(driver);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		}

	}

	@AfterTest
	public void afterMethodTearDown() {

		driver.close();
	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data", groups = ("smoke"), priority = 1, enabled = true)
	public void validUserLoginLogoutTest(Hashtable<String, String> data) {

		String runMode = data.get("Runmode");

		if (runMode.equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the test as runMode equals N");
		} else if (runMode.equalsIgnoreCase("Y")) {
			System.out.println("Inside Test method...");
			ExtentListeners.test.log(Status.INFO, "Entering username as :" + data.get("UID"));
			ExtentListeners.test.log(Status.INFO, "Entering password as :" + data.get("PWD"));

			login.amazonLogin(data.get("UID"), "@" + data.get("PWD"));
			ExtentListeners.test.log(Status.INFO, "UserID and Password has been entered");
			if (login.isImportantMessageDisplayed() || login.isAuthencticationRequiredDisplayed()) {
				ExtentListeners.test.log(Status.SKIP,
						"Amazon has deducted auto login from selenuim so skipping the test ");
				throw new SkipException("Skipping the test as amazon has deducted automated login, try login later");

			} else

				System.out.println("Login is successful");

			Assert.assertTrue(login.isloginPage1stButtonDisplayed(),
					"Amazon menu is not displayed, seems login was not succesful for valid credentials");
			ExtentListeners.test.log(Status.INFO, "User with Correct username and password login is allowed to login ");
			// System.out.println("Inside amazon log out method");
			// login.amazonLogout();
		}

	}
}
