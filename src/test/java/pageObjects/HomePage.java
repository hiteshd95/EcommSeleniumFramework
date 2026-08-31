package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @FindBy(css = ".shop-menu ul.nav li a")
    List<WebElement> anonyHeaderMenu;

    @FindBy(xpath = "//a[normalize-space()='Contact us']")
    WebElement linkContactUs;

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

    public boolean getHeaderMenuNames(){
        List<String> expectedMenuNames = new ArrayList<>();
        expectedMenuNames.add("Home");
        expectedMenuNames.add("\uE8F8 Products");
        expectedMenuNames.add("Cart");
        expectedMenuNames.add("Signup / Login");
        expectedMenuNames.add("Test Cases");
        expectedMenuNames.add("API Testing");
        expectedMenuNames.add("Video Tutorials");
        expectedMenuNames.add("Contact us");

        List<String> actualMenuNames = anonyHeaderMenu.stream().map(e -> e.getText()
                        .trim()).filter(text -> !text.isEmpty())
                        .collect(Collectors.toList());

        return areMenuListsSame(actualMenuNames, expectedMenuNames);
    }

    public void clickContactUsLink(){
        linkContactUs.click();
    }
}
