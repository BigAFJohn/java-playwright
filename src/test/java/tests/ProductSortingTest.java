package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.AssertionHelper;

import java.util.List;
import java.util.stream.Collectors;

import static io.qameta.allure.Allure.step;

public class ProductSortingTest extends BaseTest {

    @Epic("Inventory Feature")
    @Feature("Sorting")
    @Story("Sort products by name Z to A")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Sort items by name Z-A and validate the order")
    public void verifyProductSortingByNameDescending() {
        LoginPage loginPage = new LoginPage(page);
        InventoryPage inventoryPage = new InventoryPage(page);

        step("Log in to the application", () -> {
            loginPage.login(config.loginUsername(), config.loginPassword());
        });

        step("Sort items by Name Z to A", () -> inventoryPage.sortBy("za"));

        step("Verify items are sorted in descending order", () -> {
            AssertionHelper.assertExactText(String.valueOf(inventoryPage.isSortedByNameDescending()), "true");
        });

    }
}
