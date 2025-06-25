package base;

import com.microsoft.playwright.Page;
import config.AppConfig;
import core.PlaywrightManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class BaseTest {
    protected Page page;
    protected static AppConfig config = ConfigFactory.create(AppConfig.class);

    protected Logger log = LogManager.getLogger(getClass());

    public StringBuilder testLogs = new StringBuilder();
    public void log(String message) {
        log.info(message);
        Allure.step(message);
    }
    public Page getPage() {
        return page;
    }


    @BeforeMethod(alwaysRun = true)
    @Step("Set up browser and navigate to base URL")
    public void setUp() {
        config = ConfigFactory.create(AppConfig.class);
        PlaywrightManager.init();
        page = PlaywrightManager.getPage();
        log("Navigating to homepage");
        page.navigate(config.baseUrl());
        log("Homepage loaded");

        try {
            var targetPath = Paths.get("target/allure-results/environment.properties");
            if (!Files.exists(targetPath)) {
                Files.copy(
                        Paths.get("src/test/resources/environment.properties"),
                        targetPath,
                        StandardCopyOption.REPLACE_EXISTING
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @BeforeSuite
    public void setUpEnvironment() throws IOException {
        Properties props = new Properties();
        props.setProperty("OS", System.getProperty("os.name"));
        props.setProperty("Java Version", System.getProperty("java.version"));
        props.setProperty("Browser", "Playwright Chromium");

        File file = new File("target/allure-results/environment.properties");
        file.getParentFile().mkdirs();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            props.store(fos, "Allure environment properties");
        }
    }



    @AfterMethod(alwaysRun = true)
    @Step("Tear down browser")
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
        }
        PlaywrightManager.close();
    }

}