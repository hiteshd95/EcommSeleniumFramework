package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage{


    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[normalize-space()='Contact Us']")
    WebElement txtContactUs;

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement txtSetName;

    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement txtSetEmail;

    @FindBy(xpath = "//input[@placeholder='Subject']")
    WebElement txtSetSubject;

    @FindBy(xpath = "//textarea[@placeholder='Your Message Here']")
    WebElement txtSetMessage;

    @FindBy(xpath = "//input[@name='submit']")
    WebElement btnSubmit;

    @FindBy(css = ".status.alert.alert-success")
    WebElement txtSuccessMsg;

    public boolean isUserInContactUsPage(){
        return txtContactUs.isDisplayed();
    }

    public void fillContactUsFrom(String name, String email, String subject, String message){
        txtSetName.sendKeys(name);
        txtSetEmail.sendKeys(email);
        txtSetSubject.sendKeys(subject);
        txtSetMessage.sendKeys(message);
    }

    public void clickSubmitBtn(){
        btnSubmit.click();
    }

    public Alert switchToAlert(WebDriver driver){
        return driver.switchTo().alert();
    }

    public String getSuccessMessage(){
        return txtSuccessMsg.getText();
    }


}
