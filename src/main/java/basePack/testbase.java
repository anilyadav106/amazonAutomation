package basePack;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testbase {

	public static WebDriver driver;
	public static Logger log = Logger.getLogger("devpinoyLogger");

	public static void launchbrowser() {
		log.debug("Setting up chrome driver !");
		if (constants.browser.equalsIgnoreCase("chrome")) {
			log.debug("Setting up chrome driver !");
			WebDriverManager.chromedriver().setup();
			log.debug("Setting up chrome driver done!");
			driver = new ChromeDriver();

		} else {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@BeforeSuite

	public static void beforeSuite() {
		log.debug("Setting up before suite driver !");
		System.out.println("Before suite");
	}

	@AfterSuite

	public static void afterSuite() {

		System.out.println("After suite");
	}

	public static void captureScreeshot() {

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(source, constants.destination);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}