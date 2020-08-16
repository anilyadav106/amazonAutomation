package com.amazon.automation.commonmethods;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.automation.basepack.Testbase;

public class CommonMethods extends Testbase {

	private static WebDriverWait wait = new WebDriverWait(driver, 30);

	/*
	 * method to select element from a dropdown having non-select class it
	 * accepts drodown element and order of the element in the dropdown
	 */
	public static void selectElementFromDropDownNonSelect(WebElement elem, String ddvalue) {
		Actions act = new Actions(driver);
		try {
			act.moveToElement(elem).click().build().perform();
		}

		catch (NoSuchElementException e) {
			System.out.println("No such element found :" + e.getMessage());
			e.printStackTrace();

		} catch (ElementNotVisibleException e) {
			System.out.println("Element not visible :" + e.getMessage());
			e.printStackTrace();
		} catch (ElementNotSelectableException e) {
			System.out.println("Not able to select the element :" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("An unknown exception occured:" + e.getMessage());
			e.printStackTrace();
		}
		act.sendKeys(ddvalue).perform();
	}

	/*
	 * method to select element from a dropdown having select class it accepts
	 * drodown element and text of the element in the dropdow
	 */
	public static void selectElementFromDropDownSelect(WebElement elem, String elementText) {

		try {
			elem.click();
		} catch (NoSuchElementException e) {
			System.out.println("No such element found :" + e.getMessage());
			e.printStackTrace();
		} catch (ElementNotVisibleException e) {
			System.out.println("Element not visible :" + e.getMessage());
			e.printStackTrace();
		} catch (ElementClickInterceptedException e) {
			System.out.println("Not able to click on element, something interrupted the click :" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Element not selectable in the drpdown  ");
			e.printStackTrace();
		}

		Select sel = new Select(elem);

		try {
			sel.selectByVisibleText(elementText);
		} catch (ElementNotSelectableException e) {
			System.out.println("Not able to select the element :" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Not able to select the element ,an unknown exception occured:" + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void searchInputBox(WebElement elem, String searchText) {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(elem)).click();

		} catch (ElementNotSelectableException e) {
			System.out.println("Not able to select the element :" + e.getMessage());
			e.printStackTrace();
		} catch (ElementNotVisibleException e) {
			System.out.println("Element not visible :" + e.getMessage());
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("No such element found :" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("An unknown exception occured:" + e.getMessage());
			e.printStackTrace();
		}
		elem.sendKeys(searchText);
		elem.submit();
	}

	/*
	 * Static Method to launch capture screenshot in case of failure of test
	 * case. Failure of TC is decided from listener class' onTestFailure method
	 */

	public static String captureScreeshot(String testMethodName) {
		// log.debug("Launching the capture screen shot");
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String fileName = d.toString().replace(":", "-").replace(" ", "-");
		String path = System.getProperty("user.dir") + "/FailedTestCasesScreenshots/" + fileName + " " + testMethodName
				+ ".png";
		File destination = new File(path);

		try {

			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			System.out.println("Error in capturing screen shot" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("An unknown exception occured while capturing screen shot:" + e.getMessage());
			e.printStackTrace();
		}
		return path;
	}

}
