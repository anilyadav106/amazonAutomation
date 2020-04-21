package com.amazon.automation.basepack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testbase {

	protected static Properties config = new Properties();
	private FileInputStream fis;
	protected static WebDriver driver;
	protected static Logger log = Logger.getLogger(Testbase.class);

	/*
	 * method to launch the browser basis the value provided from configuration
	 * file
	 */

	public void launchBrowser() throws IOException {

		try {
			fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
			config.load(fis);
		} catch (IOException e) {
			System.out.println("unable to read the config properties file");
			e.printStackTrace();
		}

		log.debug("Launching the desired browser");
		if (config.getProperty("browser").contains("CHROME")) {
			log.debug("Launching chrome browser");

			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
			options.addArguments("disable-infobars");
			driver = new ChromeDriver(options); // add option to constructor

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

		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(config.getProperty("pageLoadTime")),
				TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getProperty("implicitWait")), TimeUnit.SECONDS);

	}

}