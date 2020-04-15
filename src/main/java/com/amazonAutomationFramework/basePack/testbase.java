package com.amazonAutomationFramework.basePack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import com.amazonAutomationFramework.constants.constants;
import com.amazonFramework.utilities.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testbase {

	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static WebDriver driver;
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\Excel\\TestData.xlsx");
	public static Logger log = Logger.getLogger(testbase.class);

	/*
	 * method to launch the browser basis the value provided from configuration
	 * file
	 */
	public static void launchbrowser() throws IOException {
		fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
		config.load(fis);

		log.debug("Launching the desired browser");
		if (config.getProperty("browser").contains("CHROME")) {
			log.debug("Launching chrome browser");
			// WebDriverManager.chromedriver().version("2.00").setup(); //
			// specific version of chromeDriver
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
			driver = new ChromeDriver(options);

		} else if (config.getProperty("browser").contains("FF")) {
			log.debug("Launching FF browser");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}

		else if (config.getProperty("browser").contains("IE")) {
			log.debug("Launching IE browser");
			WebDriverManager.iedriver().setup();
			InternetExplorerOptions options = new InternetExplorerOptions();

			options.ignoreZoomSettings();

			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			driver = new InternetExplorerDriver(options);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	/*
	 * Static Method to launch capture screenshot in case of failure of test
	 * case. Failure of TC is decided from listener class' onTestFailure method
	 */

	public static void captureScreeshot() {
		log.debug("Launching the capture screen shot");

		try {
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, constants.destination);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
