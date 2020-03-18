package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basePack.testbase;

public class homePage extends testbase {

	public homePage(WebDriver driver) {

		testbase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@name='Login']")
	public WebElement loginButton;

	@FindBy(xpath = "//input[@name='password']")
	public WebElement userIDButton;

	@FindBy(xpath = "//input[@name='username']")
	public WebElement passwordButton;

	@FindBy(xpath = "//button[@type='button']")
	public WebElement login;

	@FindBy(xpath = "//a[text()='add movie' and @class='navLink']")
	public WebElement addMovieButton;

	public void Login(String uname, String password) {

		userIDButton.sendKeys(uname);
		passwordButton.sendKeys(password);
		login.click();
	}

}
