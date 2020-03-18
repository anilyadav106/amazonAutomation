package com.amazonAutomationFramework.basePack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
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

	/*
	 * method to launch the browser basis the value provided from config file it
	 * can launch chrome,firefox or IE browsers
	 */
	public void launchbrowser() throws IOException {
		fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
		config.load(fis);

		if (config.getProperty("browser").contains("CHROME")) {

			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
			driver = new ChromeDriver(options);

		} else if (config.getProperty("browser").contains("FF")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}

		else if (config.getProperty("browser").contains("IE")) {

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

	/* method to launch the browser before starting testNG suite xml */

	public static void captureScreeshot() {

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(source, constants.destination);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
