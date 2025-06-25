package listeners;
import java.io.ByteArrayInputStream;

import base.BaseTest;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestListener implements ITestListener {
    private static final Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Starting test: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        Page page = ((BaseTest) testClass).getPage();


        // Take screenshot
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));

        // Attach screenshot to Allure
        Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));

        // Optionally log current URL
        Allure.addAttachment("Current URL", page.url());

        // Custom logs
        String logs = ((BaseTest) testClass).testLogs.toString();
        Allure.addAttachment("Execution Logs", "text/plain", logs, ".log");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test skipped: {}", result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Starting test suite: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Finished test suite: {}", context.getName());
    }
}