package pages;

import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {
    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private final Page page;

    private final String usernameInput = "#user-name";
    private final String passwordInput = "#password";
    private final String loginButton = "#login-button";
    private final String appLogo = ".app_logo";
    private final String errorMessage = "[data-test='error']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login(String username, String password) {
        log.info("Entering username: {}", username);
        page.fill(usernameInput, username);

        log.info("Entering password.");
        page.fill(passwordInput, password);

        log.info("Clicking login button.");
        page.click(loginButton);
    }

    public String getHeaderText() {
        log.info("Getting header text.");
        return page.textContent(appLogo);
    }
    public String getErrorMessage() {
        return page.textContent(errorMessage);
    }
}
