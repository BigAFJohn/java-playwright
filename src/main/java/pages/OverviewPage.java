package pages;

import com.microsoft.playwright.Page;

public class OverviewPage {
    private final Page page;

    public OverviewPage(Page page) {
        this.page = page;
    }

    public void clickFinish() {
        page.click("[data-test=finish]");
    }
}
