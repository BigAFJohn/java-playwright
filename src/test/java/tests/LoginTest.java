package tests;

import io.qameta.allure.*;
import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.AssertionHelper;

import static io.qameta.allure.Allure.step;
import org.testng.annotations.DataProvider;

import java.util.Map;

public class LoginTest extends BaseTest {

    private static final Map<String, String> messages = Map.of(
            "success", "Swag Labs",
            "locked", "Epic sadface: Sorry, this user has been locked out."
    );

    @Epic("Login Feature")
    @Feature("Parameterized login")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "loginUsers", description = "Login with valid and invalid users")
    public void loginTest(String username, String expectedMessageKey) {
        LoginPage loginPage = new LoginPage(page);

        step("Log in with: " + username, () -> {
            loginPage.login(username, config.loginPassword());
        });

        step("Verify login result", () -> {
            String actualText = username.equals(config.invalidUser())
                    ? loginPage.getErrorMessage()
                    : loginPage.getHeaderText();

            String expectedText = messages.get(expectedMessageKey);
            AssertionHelper.assertExactText(actualText, expectedText);
        });
    }

    @DataProvider(name = "loginUsers")
    public Object[][] loginUsers() {
        return new Object[][]{
                {config.loginUsername(), "success"},
                {config.invalidUser(), "locked"}
        };
    }
}
