package testcases;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class HomePageTest extends BaseClass {

    public HomePage hp;
    public ContactUsPage cp;

    @Test
    @Description("Verify home page menu options for anonymous user")
    public void headerMenuTest(){
        try {
            hp = new HomePage(driver);
            boolean isHeaderMenuListDisplayed = hp.getHeaderMenuNames();
            Assert.assertEquals(isHeaderMenuListDisplayed, true);
        }catch (Exception e) {
            logger.error("Test Failed..");
            logger.debug("Debug Logs...");
            Assert.fail();
        }
    }

    @Test
    @Description("Verify successful contact us form submission")
    public void contactUsFormTest(){
        hp = new HomePage(driver);
        hp.clickContactUsLink();

        cp = new ContactUsPage(driver);
        boolean userInContactUsPage = cp.isUserInContactUsPage();
        Assert.assertEquals(userInContactUsPage, true);

        cp.fillContactUsFrom(prop.getProperty("name"), prop.getProperty("validEmail"), prop.getProperty("subject"), prop.getProperty("message"));
        cp.clickSubmitBtn();
        cp.switchToAlert(driver).accept();

        String successMsg = cp.getSuccessMessage();
        Assert.assertEquals(successMsg, "Success! Your details have been submitted successfully.");
    }
}
