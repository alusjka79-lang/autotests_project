package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

// ТЕСТЫ СТРАНИЦЫ CART (КОРЗИНА)
public class CartTests extends BaseTest {

    @Test(description = "Добавление одного товара в корзину")
    public void addOneItemToCartTest() {
        Allure.step("Логинимся и добавляем один товар в корзину");
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver, wait);
        inventory.addBackpackToCart();
        inventory.openCart();

        CartPage cart = new CartPage(driver, wait);
        Assert.assertTrue(cart.isBackpackDisplayed(), "Backpack должен отображаться в корзине");
    }

    @Test(description = "Добавление двух товаров в корзину")
    public void addTwoItemsToCartTest() {
        Allure.step("Логинимся и добавляем два товара в корзину");
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver, wait);
        inventory.addBackpackToCart();
        inventory.addBikeLightToCart();
        inventory.openCart();

        CartPage cart = new CartPage(driver, wait);
        Assert.assertTrue(cart.isBackpackDisplayed(), "Backpack должен отображаться в корзине");
        Assert.assertTrue(cart.isBikeLightDisplayed(), "Bike Light должен отображаться в корзине");
    }

    @Test(description = "Удаление товара из корзины")
    public void removeItemFromCartTest() {
        Allure.step("Добавляем товар и затем удаляем его из корзины");
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver, wait);
        inventory.addBackpackToCart();
        inventory.openCart();

        CartPage cart = new CartPage(driver, wait);
        cart.removeBackpack();

        Assert.assertFalse(cart.isBackpackPresent(), "Backpack должен быть удален из корзины");
    }

    @Test(description = "Проверка сохранения содержимого корзины после обновления страницы")
    public void cartPersistsAfterPageRefreshTest() {
        Allure.step("Добавляем товар, обновляем страницу и проверяем сохранение корзины");
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver, wait);
        inventory.addBackpackToCart();
        inventory.openCart();

        driver.navigate().refresh();

        CartPage cart = new CartPage(driver, wait);
        Assert.assertTrue(cart.isBackpackDisplayed(), "Backpack должен остаться в корзине после обновления страницы");
    }

    @Test(description = "Попытка перейти к Checkout с пустой корзиной (найден баг сайта)")
    public void checkoutWithEmptyCartTest() {
        Allure.step("Пробуем оформить заказ с пустой корзиной");
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver, wait);
        inventory.openCart();

        CartPage cart = new CartPage(driver, wait);
        cart.checkout();

        // ВНИМАНИЕ: сайт saucedemo.com не проверяет пустую корзину и пропускает на checkout-step-one.
        // Это баг приложения. Тест документирует реальное (некорректное) поведение сайта.
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"),
                "Баг сайта: система позволяет перейти к оформлению заказа с пустой корзиной");
    }
}
