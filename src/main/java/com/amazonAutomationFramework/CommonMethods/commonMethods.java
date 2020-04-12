package com.amazonAutomationFramework.CommonMethods;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazonAutomationFramework.basePack.testbase;

public class commonMethods extends testbase {

	public static WebDriverWait wait = new WebDriverWait(driver, 30);

	/*
	 * method to select element from a dropdown having non-select class it
	 * accepts drodown element and order of the element in the dropdown
	 */
	public static void selectElementFromDropDownNonSelect(WebElement elem, String ddvalue) {

		try {

			Actions act = new Actions(driver);
			act.moveToElement(elem).click().perform();
			act.sendKeys(ddvalue).perform();

		} catch (Exception e) {
			System.out.println("Element not selectable in the drpdown   : ");
			e.printStackTrace();
		}

	}

	/*
	 * method to select element from a dropdown having select class it accepts
	 * drodown element and text of the element in the dropdow
	 */
	public static void selectElementFromDropDownSelect(WebElement elem, String elementText) {
		elem.click();
		Select sel = new Select(elem);
		try {

			sel.selectByVisibleText(elementText);

		} catch (Exception e) {
			System.out.println("Element not selectable in the drpdown   : ");
			e.printStackTrace();
		}

	}

	public static void searchInputBox(WebElement elem, String searchText) {
		wait.until(ExpectedConditions.elementToBeClickable(elem)).click();
		try {

			elem.sendKeys(searchText);
			elem.submit();

		} catch (Exception e) {
			System.out.println("Not able to enter into search box ");
			e.printStackTrace();
		}

	}

}
