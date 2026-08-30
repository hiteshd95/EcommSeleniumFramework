package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Signup / Login']")
    WebElement linkLoginSingup;

    @FindBy(xpath = "//*[contains(text(), 'Logged in as ')]")
    WebElement txtLoggedInUser;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement linkLogut;

    public void clickLoginSignup(){
        clickElement(linkLoginSingup);
    }

    public boolean isUserLoggedIn(){
        try {
            return txtLoggedInUser.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickLogout(){
        clickElement(linkLogut);
    }
}
