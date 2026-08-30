package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class LoginRegistrationPage extends BasePage{

    public LoginRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//input[@placeholder='Name']")
    WebElement txtName;

    @FindBy(xpath="//input[@data-qa='signup-email']")
    WebElement txtEmail;

    @FindBy(xpath="//button[normalize-space()='Signup']")
    WebElement btnSignup;

    @FindBy(xpath = "//input[@id='id_gender1']")
    WebElement checkBoxSalutation;

    @FindBy(xpath = "//input[@id='password']")
    WebElement txtPassword;

    @FindBy(xpath = "//select[@id='days']")
    WebElement dobDay;

    @FindBy(xpath = "//select[@id='months']")
    WebElement dobMonth;

    @FindBy(xpath = "//select[@id='years']")
    WebElement dobYear;

    @FindBy(xpath = "//input[@id='first_name']")
    WebElement txtFirstName;

    @FindBy(xpath = "//input[@id='last_name']")
    WebElement txtLastName;

    @FindBy(xpath = "//input[@id='address1']")
    WebElement txtAddress1;

    @FindBy(xpath = "//select[@id='country']")
    WebElement dropCountry;

    @FindBy(xpath = "//input[@id='state']")
    WebElement txtState;

    @FindBy(xpath = "//input[@id='city']")
    WebElement txtCity;

    @FindBy(xpath = "//input[@id='zipcode']")
    WebElement txtZipcode;

    @FindBy(xpath = "//input[@id='mobile_number']")
    WebElement txtMobileNumber;

    @FindBy(xpath = "//button[normalize-space()='Create Account']")
    WebElement btnCreateAccount;

    @FindBy(xpath = "//h2[normalize-space()='Account Created!']")
    WebElement confMsg;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    WebElement txtLoginEmail;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    WebElement txtLoginPassword;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement btnLogin;


    public void setName(String name){
        txtName.sendKeys(name);
    }

    public void setEmail(String email){
        txtEmail.sendKeys(email);
    }

    public void clickSignup(){
        clickElement(btnSignup);
    }

    public void checkSalutation(){
        checkBoxSalutation.click();
    }

    public void setPassword(String pass){
        txtPassword.sendKeys(pass);
    }

    public void setDob(String day, String month, String year){
        scrollIntoView(dobDay);
        Select daySelect = new Select(dobDay);
        daySelect.selectByValue(day);
        Select monthSelect = new Select(dobMonth);
        monthSelect.selectByVisibleText(month);
        Select yearSelect = new Select(dobYear);
        yearSelect.selectByValue(year);
    }

    public void setFirtName(String fname){
        scrollIntoView(txtFirstName);
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname){
        scrollIntoView(txtLastName);
        txtLastName.sendKeys(lname);
    }

    public void setAddress1(String address1){
        scrollIntoView(txtAddress1);
        txtAddress1.sendKeys(address1);
    }

    public void setCountry(String country){
        scrollIntoView(dropCountry);
        Select selectCountry = new Select(dropCountry);
        selectCountry.selectByValue(country);
    }

    public void setState(String state){
        scrollIntoView(txtState);
        txtState.sendKeys(state);
    }

    public void setCity(String city){
        scrollIntoView(txtCity);
        txtCity.sendKeys(city);
    }

    public void setZipcode(String zipcode){
        scrollIntoView(txtZipcode);
        txtZipcode.sendKeys(zipcode);
    }

    public void setMobileNumber(String mobileNumber){
        scrollIntoView(txtMobileNumber);
        txtMobileNumber.sendKeys(mobileNumber);
    }

    public void clickCreateAccount(){
        scrollIntoView(btnCreateAccount);
        clickElement(btnCreateAccount);
    }

    public String getConfirmationMsg(){
        scrollIntoView(confMsg);
        try{
            return confMsg.getText();
        }catch(Exception e){
            return e.getMessage();
        }

    }

    public void setLoginEmail(String email){
        txtLoginEmail.sendKeys(email);
    }

    public void setLoginPassword(String password){
        txtLoginPassword.sendKeys(password);
    }

    public void clickLogin(){
        clickElement(btnLogin);
    }


}
