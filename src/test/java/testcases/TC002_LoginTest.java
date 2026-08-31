package testcases;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginRegistrationPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC002_LoginTest extends BaseClass {

    public HomePage hp;
    public LoginRegistrationPage rp;

    @Test
    @Description("Verify login")
    @Epic("EP001")
    @Feature("Feature 2: User Login")
    @Step("Verify valid user login")
    @Severity(SeverityLevel.BLOCKER)
    public void verifyValidLogin(){
        logger.info("*******Startiing TC002_LoginTest*******");

        try {
            hp = new HomePage(driver);
            hp.clickLoginSignup();
            logger.info("Clicked on login/signup button");

            rp = new LoginRegistrationPage(driver);
            logger.info("Providing user login details");
            rp.setLoginEmail(prop.getProperty("validEmail"));
            rp.setLoginPassword(prop.getProperty("validPassword"));
            rp.clickLogin();
            logger.info("Validating if user is logged in");
            boolean userLoggedIn = hp.isUserLoggedIn();
            Assert.assertEquals(userLoggedIn, true);
            hp.clickLogout();
        }catch(Exception e){
            logger.error("Test Failed..");
            logger.debug("Debug Logs...");
            Assert.fail();
        }
    }

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    @Description("Verify data driven login for valid and invalid users")
    @Epic("EP001")
    @Feature("Feature 2: User Login")
    @Step("Verify valid and invalid user login")
    @Severity(SeverityLevel.BLOCKER)
    public void verifyLoginDDT(String email, String password, String expected){
        logger.info("*******Starting TC003_Login Data Driven Test*******");

        try {
            hp = new HomePage(driver);
            hp.clickLoginSignup();
            logger.info("Clicked on login/signup button");

            rp = new LoginRegistrationPage(driver);
            logger.info("Providing user login details");
            rp.setLoginEmail(email);
            rp.setLoginPassword(password);
            rp.clickLogin();
            logger.info("Validating if user is logged in");
            boolean userLoggedIn = hp.isUserLoggedIn();

            if(expected.equalsIgnoreCase("valid")){
                if(userLoggedIn){
                    hp.clickLogout();
                    Assert.assertTrue(true);
                }else {
                    Assert.assertTrue(false);
                }
            } else if (expected.equalsIgnoreCase("invalid")) {
                if(!userLoggedIn){
                    Assert.assertTrue(true);
                }else {
                    hp.clickLogout();
                    Assert.assertTrue(false);
                }
            }
        }catch(Exception e){
            logger.error("Test Failed..");
            logger.debug("Debug Logs...");
            Assert.fail("Exception while executing TC003: " + e.getMessage());
        }
    }

}
