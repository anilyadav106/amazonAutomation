package com.amazon.automation.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.automation.commonmethods.CommonMethods;

public class HomePage {
	/*
	 * Constructor- Driver is passed from test class at the time of creating
	 * object of this class and that driver will do all required stuff here
	 */
	private WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//select[@id='searchDropdownBox']")
	private WebElement categorySelectButton;

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	private WebElement searchBar;

	@FindBy(xpath = "//form[@class='nav-searchbar']//input[@type='submit']")
	private WebElement searchGoButton;
	@FindBy(xpath = "//a[text()='Smartwatches']")
	private WebElement smartWatchesButton;
	@FindBy(xpath = "//div[@class='sg-col-inner']//span[contains(text(),'Apple iPhone 6s (32GB) - Space Grey')]")
	private WebElement iPhoneSearchResult;

	@FindBy(xpath = "//input[@id='add-to-cart-button']")
	private WebElement addCartButton;

	@FindBy(xpath = "//h1[contains(text() ,'Added to Cart')]")
	private WebElement AddedToCartSuccessMessage;

	private String expectedTitle = "Buy Watches Online at Best Prices in India | Buy Wrist & Digital Watches Online - Amazon.in";

	/*
	 * method to select a category from the drop-down on home page and search
	 * all such products
	 * 
	 * String category is the category to search.
	 */

	public void selectCategory(String category) {
		WebDriverWait wait = new WebDriverWait(driver, 40);

		CommonMethods.selectElementFromDropDownSelect(categorySelectButton, category);

		searchGoButton.click();

		wait.until(ExpectedConditions.elementToBeClickable(smartWatchesButton));

	}

	/*
	 * method to search a product and add to cart
	 * 
	 * String searchText is the product to search.
	 */
	public void addtoCartAProduct(String searchText) {
		WebDriverWait wait = new WebDriverWait(driver, 60);

		CommonMethods.searchInputBox(searchBar, searchText);

		searchGoButton.click();

		String parentHandel = driver.getWindowHandle();

		// WebElement elem = driver.findElement(By.xpath("//img[@alt='" +
		// searchText + "']"));

		try {

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='" + searchText + "']"))).click();
		} catch (StaleElementReferenceException e) {
			driver.findElement(By.xpath("//img[@alt='" + searchText + "']"));
			e.printStackTrace();
		}

		/*
		 * converting set of window handles into array list of generic
		 * type=String , so that indexing can be applied on Set values
		 */

		ArrayList<String> tab2 = new ArrayList<String>(driver.getWindowHandles());
		if (tab2.size() > 1) {

			tab2.remove(parentHandel); /*
										 * to remove window handle of 2nd opened
										 * tab from the array list.
										 */

			driver.switchTo().window(tab2.get(0));

			wait.until(ExpectedConditions.visibilityOf(addCartButton)).click();

			wait.until(ExpectedConditions.visibilityOf(getAddedToCartSuccessMessage()));

		} else {

			wait.until(ExpectedConditions.visibilityOf(addCartButton)).click();

			wait.until(ExpectedConditions.visibilityOf(getAddedToCartSuccessMessage()));

		}

	}

	public WebElement getAddedToCartSuccessMessage() {
		return AddedToCartSuccessMessage;
	}

	public void setAddedToCartSuccessMessage(WebElement addedToCartSuccessMessage) {
		this.AddedToCartSuccessMessage = addedToCartSuccessMessage;
	}

	public String getExpectedTitle() {
		return expectedTitle;
	}

	public void setExpectedTitle(String expectedTitle) {
		this.expectedTitle = expectedTitle;
	}

}
