package utilities;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();

        if (testInstance instanceof BaseClass) {
            BaseClass base = (BaseClass) testInstance;

            try {
                String screenshotPath = base.captureScreen(result.getName());
                byte[] screenshotBytes = Files.readAllBytes(Path.of(screenshotPath));

                Allure.addAttachment(
                        "Failure Screenshot",
                        "image/png",
                        new ByteArrayInputStream(screenshotBytes),
                        ".png"
                );

                if (result.getThrowable() != null) {
                    Allure.addAttachment("Failure Message", "text/plain", result.getThrowable().toString(), ".txt");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.addAttachment("Skipped Test", "text/plain", "Test skipped: " + result.getName(), ".txt");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.addAttachment("Passed Test", "text/plain", "Test passed: " + result.getName(), ".txt");
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}