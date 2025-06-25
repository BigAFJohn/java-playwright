package pages;

import com.microsoft.playwright.Page;

public class CheckoutPage {
    private final Page page;

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public void fillCustomerDetails(String firstName, String lastName, String postalCode) {
        page.fill("[data-test=firstName]", firstName);
        page.fill("[data-test=lastName]", lastName);
        page.fill("[data-test=postalCode]", postalCode);
    }

    public void clickContinue() {
        page.click("[data-test=continue]");
    }
}
