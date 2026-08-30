package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties prop;

    @BeforeClass
    @Parameters({"os", "browser"})
    public void setup(@Optional("windows") String os, @Optional("chrome")String br) throws InterruptedException, IOException {

        // loading config.peroperties file
        FileReader file = new FileReader("./src//test//resources//config.properties");
        prop = new Properties();
        prop.load(file);

        logger = LogManager.getLogger(this.getClass());

        logger.info("Running on operating system: {}", os);


        if(prop.getProperty("execution_env").equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities = new DesiredCapabilities();

            if(os.equalsIgnoreCase("windows")){
                capabilities.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.MAC);
            }else if (os.equalsIgnoreCase("linux")) {
                capabilities.setPlatform(Platform.LINUX);
            }else{
                System.out.println("No Matching OS found");
                return;
            }

            switch (br.toLowerCase()){
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
                case "firefox": capabilities.setBrowserName("firefox");break;
                default:System.out.println("No Matching Browser");return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        }else if(prop.getProperty("execution_env").equalsIgnoreCase("local")){
            switch (br.toLowerCase()){
                case "chrome": driver = new ChromeDriver(); break;
                case "edge": driver = new EdgeDriver(); break;
                case "firefox" : driver = new FirefoxDriver(); break;
                default: System.out.println("Invalid Browser Name..."); return;
            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("URL"));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public String randomString(){
        String emailString = RandomStringUtils.randomAlphabetic(7);
        return emailString+ "@gmail.com";
    }

    public String randomStringForPass(){
        String alphabets = RandomStringUtils.randomAlphabetic(4);
        String numbers = RandomStringUtils.randomNumeric(3);
        String password = alphabets + "@" + numbers;
        return password;
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        String folderPath = System.getProperty("user.dir") + "\\screenshots";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath = folderPath + "\\" + tname + "_" + timeStamp + ".png";
        File destFile = new File(filePath);

        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        org.apache.commons.io.FileUtils.copyFile(sourceFile, destFile);

        return filePath;
    }


}
