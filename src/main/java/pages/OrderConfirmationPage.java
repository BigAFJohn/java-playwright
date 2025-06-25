package pages;

import com.microsoft.playwright.Page;

public class OrderConfirmationPage {
    private final Page page;

    private static final String COMPLETE_HEADER = "[data-test=complete-header]";

    public OrderConfirmationPage(Page page) {
        this.page = page;
    }

    public String getConfirmationMessage() {
        return page.locator(COMPLETE_HEADER).innerText();
    }
}
