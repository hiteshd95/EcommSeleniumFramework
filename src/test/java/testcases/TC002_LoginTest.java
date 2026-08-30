package testcases;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginRegistrationPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

    @Test
    @Description("Verify login")
    @Epic("EP001")
    @Feature("Feature 2: User Login")
    @Step("Verify valid user login")
    @Severity(SeverityLevel.BLOCKER)
    public void verifyValidLogin(){
        logger.info("*******Startiing TC002_LoginTest*******");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickLoginSignup();
            logger.info("Clicked on login/signup button");

            LoginRegistrationPage rp = new LoginRegistrationPage(driver);
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
}
