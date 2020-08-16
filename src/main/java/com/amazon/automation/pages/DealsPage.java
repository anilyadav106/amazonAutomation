package com.amazon.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DealsPage {
	/*
	 * Constructor- Driver is passed from test class at the time of creating
	 * object of this class and that driver will do all required stuff here
	 */
	private WebDriver driver;

	public DealsPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	private WebElement todaysDealsButton;

	@FindBy(xpath = "(//div[@id='widgetFilters']//span[contains(text(),'Books')])[1]")
	private WebElement booksCheckbox;

	public void selectDealsByRating(String dealsCategory) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		todaysDealsButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//div[@id='widgetFilters']//span[contains(text(),'" + dealsCategory + "')])[1]"))).click();

	}

	public Boolean dealsCategorySearchText(String dealsCategory) {

		return driver
				.findElement(By.xpath(
						"//*[@id='FilterItemView_all_summary']/div//a//span[contains(text(),'" + dealsCategory + "')]"))
				.isDisplayed();
	}

}
