package pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    public List<InventoryPage.Product> getCartItems() {
        List<ElementHandle> names = page.querySelectorAll("[data-test=inventory-item-name]");
        List<ElementHandle> prices = page.querySelectorAll("[data-test=inventory-item-price]");

        List<InventoryPage.Product> products = new ArrayList<>();

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i).innerText();
            String price = prices.get(i).innerText();
            products.add(new InventoryPage.Product(name, price));
        }

        return products;
    }

    public void clickCheckout() {
        page.click("[data-test=checkout]");
    }
}
