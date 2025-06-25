package core;

import com.microsoft.playwright.*;
import config.AppConfig;
import org.aeonbits.owner.ConfigFactory;

public class PlaywrightManager {
    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    private static final AppConfig config = ConfigFactory.create(AppConfig.class);

    public static void init() {
        playwright.set(Playwright.create());

        String browserType = System.getProperty("browser", config.browser());
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", String.valueOf(config.headless())));

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(isHeadless);

        switch (browserType.toLowerCase()) {
            case "firefox":
                browser.set(playwright.get().firefox().launch(options));
                break;
            case "webkit":
                browser.set(playwright.get().webkit().launch(options));
                break;
            case "chromium":
            default:
                browser.set(playwright.get().chromium().launch(options));
                break;
        }

        context.set(browser.get().newContext());
        page.set(context.get().newPage());
    }

    public static Page getPage() {
        return page.get();
    }

    public static void close() {
        if (page.get() != null) page.get().close();
        if (context.get() != null) context.get().close();
        if (browser.get() != null) browser.get().close();
        if (playwright.get() != null) playwright.get().close();
    }
}
