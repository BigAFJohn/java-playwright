package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.*;
import utils.AssertionHelper;
import utils.DataFakerUtil;

import java.util.List;

import static io.qameta.allure.Allure.step;

public class CheckoutTest extends BaseTest {

    @Epic("Checkout Feature")
    @Feature("Add to cart and checkout")
    @Story("Random product selection and cart verification")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login, select two random products, add to cart, go to cart page and proceed to checkout")
    public void checkoutTest() {
        LoginPage loginPage = new LoginPage(page);
        InventoryPage inventoryPage = new InventoryPage(page);
        CartPage cartPage = new CartPage(page);
        CheckoutPage checkoutPage = new CheckoutPage(page);
        OverviewPage overviewPage = new OverviewPage(page);
        OrderConfirmationPage confirmationPage = new OrderConfirmationPage(page);


        String firstName = DataFakerUtil.getRandomFirstName();
        String lastName = DataFakerUtil.getRandomLastName();
        String postalCode = DataFakerUtil.getRandomZipCode();

        step("Log in to the application", () -> {
            loginPage.login(config.loginUsername(), config.loginPassword());
        });

        List<InventoryPage.Product> selected = step("Add two random products to cart", () -> {
            List<InventoryPage.Product> products = inventoryPage.addRandomProductsToCart(2);
            products.forEach(p -> System.out.println("Selected product: " + p.name + " - " + p.price));
            return products;
        });

        step("Proceed to cart", inventoryPage::goToCart);

        List<InventoryPage.Product> cartItems = cartPage.getCartItems();

        step("Validate cart contains the selected products", () -> {
            for (InventoryPage.Product expected : selected) {
                boolean matchFound = cartItems.stream()
                        .anyMatch(actual -> actual.name.equals(expected.name) && actual.price.equals(expected.price));
                AssertionHelper.assertExactText(String.valueOf(matchFound), "true");
            }
        });

        step("Click checkout", cartPage::clickCheckout);

        step("Fill checkout form and continue", () -> {
            checkoutPage.fillCustomerDetails(firstName, lastName, postalCode);
            checkoutPage.clickContinue();
        });

        step("Click finish on overview page", overviewPage::clickFinish);

        step("Verify order confirmation message", () -> {
            String message = confirmationPage.getConfirmationMessage();
            AssertionHelper.assertExactText(message, "Thank you for your order!");
        });

    }
}
