package testcases;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginRegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test
    @Description("Verify New User Registration")
    @Epic("EP001")
    @Feature("Feature 1: New User registration")
    @Step("Verify user registration")
    @Severity(SeverityLevel.BLOCKER)
    public void verifyAccountRegistration(){

        logger.info("*******Startiing TC001_AccountRegistrationTest*******");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickLoginSignup();
            logger.info("Clicked on login/signup button");

            LoginRegistrationPage rp = new LoginRegistrationPage(driver);
            logger.info("Providing new user details");
            rp.setName(prop.getProperty("name"));
            String email = randomString();
            rp.setEmail(email);
            rp.clickSignup();

            rp.checkSalutation();
            String password = randomStringForPass();
            rp.setPassword(password);
            rp.setDob(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
            rp.setFirtName(prop.getProperty("name"));
            rp.setLastName(prop.getProperty("lastName"));
            rp.setAddress1(prop.getProperty("address1"));
            rp.setCountry(prop.getProperty("country"));
            rp.setState(prop.getProperty("state"));
            rp.setCity(prop.getProperty("city"));
            rp.setZipcode(prop.getProperty("zipcode"));
            rp.setMobileNumber(prop.getProperty("mobileNumber"));
            rp.clickCreateAccount();
            logger.info("Validating expected message");
            String confmMessage = rp.getConfirmationMsg();
            Assert.assertEquals(confmMessage, "ACCOUNT CREATED!");
        }catch (Exception e){
            logger.error("Test Failed..");
            logger.debug("Debug Logs...");
            Assert.fail();
        }

    }


}
