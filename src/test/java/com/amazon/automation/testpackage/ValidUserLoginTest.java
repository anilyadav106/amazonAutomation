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
import com.amazon.automation.pages.LoginPage;
import com.aventstack.extentreports.Status;

public class ValidUserLoginTest extends Testbase {

	private LoginPage login;
	private WebDriver driver;
	@BeforeTest
	public void testSetup() {

		try {
			driver=	launchBrowser();
			driver.get(config.getProperty("baseURL"));
			login = new LoginPage(driver);

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

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data", groups = ("smoke"), priority = 1, enabled = true)
	public void validUserLoginLogoutTest(Hashtable<String, String> data) {

		String runMode = data.get("Runmode");

		if (runMode.equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the test as runMode equals N");
		} else if (runMode.equalsIgnoreCase("Y")) {
			log.debug("Entering valid UID and PWD");
			ExtentListeners.test.log(Status.INFO, "Entering username as :" + data.get("UID"));
			ExtentListeners.test.log(Status.INFO, "Entering password as :" + data.get("PWD"));

			login.amazonLogin(data.get("UID"), "@" + data.get("PWD"));
			ExtentListeners.test.log(Status.INFO, "UserID and Password has been entered");
			if (login.isImportantMessageDisplayed() || login.isAuthencticationRequiredDisplayed()) {
				ExtentListeners.test.log(Status.SKIP,
						"Amazon has deducted auto login from selenuim so skipping the test ");
				log.debug("Skipping the test as Amazon has deducted auto login from selenuim so skipping the test");
				throw new SkipException("Skipping the test as amazon has deducted automated login, try login later");

			} else

				System.out.println("Login is successful");

			Assert.assertTrue(login.isloginPage1stButtonDisplayed(),
					"Amazon menu is not displayed, seems login was not succesful for valid credentials");
			ExtentListeners.test.log(Status.INFO, "User with Correct username and password login is allowed to login ");
			log.debug("User with Correct username and password login is allowed to login");
		}

	}
}