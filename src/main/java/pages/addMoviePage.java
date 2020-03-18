package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import basePack.testbase;

public class addMoviePage extends testbase {

	public addMoviePage(WebDriver driver) {

		testbase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='title']")
	public WebElement titleTextBox;

	@FindBy(xpath = "//label[text()='Director']")
	public WebElement directorTextBox;

	@FindBy(xpath = "//textarea[@name='description']")
	public WebElement descriptionTextBox;

	@FindBy(xpath = "//select[@name='categories']")
	public WebElement categoryDD;

	@FindBy(xpath = "//input[@name='file']")
	public WebElement urlTextBox;

	@FindBy(xpath = "//button[text()='Save Movie']")
	public WebElement saveMovieButton;

	public void selectMovie(String name) {

		Select sel = new Select(categoryDD);
		sel.selectByVisibleText(name);
	}

}
