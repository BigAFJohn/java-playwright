package pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import java.util.*;

public class InventoryPage {
    private final Page page;

    public InventoryPage(Page page) {
        this.page = page;
    }

    public List<Product> addRandomProductsToCart(int count) {
        List<ElementHandle> items = page.querySelectorAll("[data-test=inventory-item-name]");
        List<ElementHandle> prices = page.querySelectorAll("[data-test=inventory-item-price]");
        List<ElementHandle> addButtons = page.querySelectorAll("[data-test^='add-to-cart']");

        List<Product> selected = new ArrayList<>();
        Random random = new Random();
        Set<Integer> pickedIndexes = new HashSet<>();

        while (pickedIndexes.size() < count) {
            pickedIndexes.add(random.nextInt(items.size()));
        }

        for (int index : pickedIndexes) {
            String name = items.get(index).innerText();
            String price = prices.get(index).innerText();
            String addButtonTestId = addButtons.get(index).getAttribute("data-test");

            page.click("[data-test='" + addButtonTestId + "']");
            selected.add(new Product(name, price));
        }

        return selected;
    }

    public void goToCart() {
        page.click("[data-test=shopping-cart-link]");
    }

    public static class Product {
        public final String name;
        public final String price;

        public Product(String name, String price) {
            this.name = name;
            this.price = price;
        }
    }

    public void sortBy(String optionValue) {
        page.selectOption("[data-test=product-sort-container]", optionValue); // e.g., "za"
    }

    public boolean isSortedByNameDescending() {
        List<String> names = page.querySelectorAll("[data-test=inventory-item-name]")
                .stream()
                .map(ElementHandle::innerText)
                .toList();

        List<String> sorted = new ArrayList<>(names);
        sorted.sort(Comparator.reverseOrder());

        return names.equals(sorted);
    }
}
