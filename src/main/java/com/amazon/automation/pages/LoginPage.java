package com.amazon.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	/*
	 * Constructor- Driver is passed from test class at the time of creating
	 * object of this class and that driver will do all required stuff here
	 */
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='nav-xshop']//a[1]")
	private WebElement loginPage1stButton;

	@FindBy(xpath = "//a[@data-nav-role='signin']")
	private WebElement loginButton;

	@FindBy(xpath = "//input[@type='email']")
	private WebElement UIDButton;

	@FindBy(xpath = "//input[@id='continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement PWDButton;

	@FindBy(xpath = "// input[@id='signInSubmit']")
	private WebElement SubmitLoginButton;

	@FindBy(xpath = "//h4[contains(text(),'Important Message!')]")
	private WebElement importantMessage;

	@FindBy(xpath = "//div[@class='a-box-inner a-alert-container']//span[contains(text(),'Your password is incorrect')]")
	private WebElement incorrectPasswordText;

	@FindBy(xpath = "//h1[text()='Authentication required']")
	private WebElement authenticationRequiredText;

	@FindBy(xpath = "//span[text()='Hello, ANIL']")
	private WebElement helloAnilButton;

	@FindBy(xpath = "//span[text()='Sign Out']")
	private WebElement logOutButton;

	@FindBy(xpath = "//i[@class='hm-icon nav-sprite']")
	private WebElement humburgerButton;

	@FindBy(xpath = "//ul[@class='hmenu hmenu-visible']//li[26]")
	private WebElement logOutButton1;

	public void amazonLogin(String uid, String pwd) {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

		wait.until(ExpectedConditions.elementToBeClickable(UIDButton)).sendKeys(uid);

		continueButton.click();

		PWDButton.clear();

		wait.until(ExpectedConditions.elementToBeClickable(PWDButton)).sendKeys(pwd);

		SubmitLoginButton.click();

	}

	public void amazonLogout() {
		WebDriverWait wait = new WebDriverWait(driver, 20);

		humburgerButton.click();

		wait.until(ExpectedConditions.elementToBeClickable(logOutButton1)).click();

		wait.until(ExpectedConditions.elementToBeClickable(UIDButton));

	}

	public Boolean isImportantMessageDisplayed() {

		return importantMessage.isDisplayed();

	}

	public Boolean isIncorrectPasswordTextDisplayed() {
		return incorrectPasswordText.isDisplayed();

	}

	public Boolean isloginPage1stButtonDisplayed() {

		return loginPage1stButton.isDisplayed();

	}

	public Boolean isAuthencticationRequiredDisplayed() {

		return authenticationRequiredText.isDisplayed();

	}

}
