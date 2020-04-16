package com.amazonAutomationFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazonAutomationFramework.CommonMethods.commonMethods;

public class couponsPage {
	/*
	 * Constructor- Driver is passed from test class at the time of creating
	 * object of this class and that driver will do all required stuff here
	 */
	private WebDriver driver;

	public couponsPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='nav-xshop']//a[text()='Coupons']")
	private WebElement couponsButton;
	@FindBy(xpath = "//a['View all coupons']//img[@alt='View all coupons']")
	private WebElement viewAllCouponsButton;
	@FindBy(xpath = "//a//img[@alt='Shop by category']")
	private WebElement couponsCategoryButton;

	@FindBy(xpath = "//span[@id='a-autoid-0-announce']")
	private WebElement sortCouponsDD;
	@FindBy(xpath = "//span[contains(text(),'All Coupons')]")
	private WebElement AllCouponsText;

	@FindBy(xpath = "//h1[text()='Watches Coupons']")
	private WebElement WatchesCouponText;

	private static String expectedWatchesTitle = "Watches coupons @ Amazon.in";

	public String getWatchesPageTitle() {

		return expectedWatchesTitle;
	}

	public void couponsSort(String sortText) {
		WebDriverWait wait = new WebDriverWait(driver, 40);

		wait.until(ExpectedConditions.elementToBeClickable(couponsButton)).click();

		wait.until(ExpectedConditions.visibilityOf(viewAllCouponsButton)).click();

		wait.until(ExpectedConditions.elementToBeClickable(sortCouponsDD));

		commonMethods.selectElementFromDropDownNonSelect(sortCouponsDD, sortText);

	}

	public void selectCouponsCategory(String category) {
		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.elementToBeClickable(couponsButton)).click();

		wait.until(ExpectedConditions.visibilityOf(couponsCategoryButton)).click();

		String CouponsCategoryxpathString = "//img[@alt='" + category + "']";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CouponsCategoryxpathString))).click();

		wait.until(ExpectedConditions.textToBePresentInElement(WatchesCouponText, "Watches Coupons"));

	}

	public boolean isAllCouponsTextDisplayed() {

		return AllCouponsText.isDisplayed();
	}

}
