package tests;

import base.BaseTest;

import org.testng.Assert;

import org.testng.annotations.Test;

import pages.*;

// ТЕСТЫ ОФОРМЛЕНИЯ ЗАКАЗА

public class CheckoutTests extends BaseTest {

    @Test(description = "Успешное оформление заказа")

    public void checkoutTest() {

        // Логин

        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров

        InventoryPage inventory = new InventoryPage(driver, wait);

        // Добавляем товар

        inventory.addBackpackToCart();

        // Переходим в корзину

        inventory.openCart();

        // Создаем страницу корзины

        CartPage cart = new CartPage(driver, wait);

        // Начинаем checkout процесс

        cart.checkout();

        // Создаем страницу checkout

        CheckoutPage checkout = new CheckoutPage(driver, wait);

        // Заполняем форму пользователя

        checkout.fillInformation("John", "Doe", "10001");

        // Завершаем заказ

        checkout.finishOrder();

        // Создаем страницу Checkout Complete Page

        CheckoutCompletePage complete = new CheckoutCompletePage(driver, wait);

        // Проверяем успешное сообщение

        Assert.assertEquals(complete.getSuccessMessage(), "Thank you for your order!");

    }

    @Test(description = "Continue shopping после корзины")

    public void continueFromCartTest() {

        // Логин

        new LoginPage(driver, wait).login("standard_user", "secret_sauce");

        // Создаем страницу товаров

        InventoryPage inventory = new InventoryPage(driver, wait);

        // Добавляем товар

        inventory.addBackpackToCart();

        // Переходим в корзину

        inventory.openCart();

        CartPage cart = new CartPage(driver, wait);

        // Возврат назад в магазин

        cart.continueShopping();

        // Проверяем что снова на странице товаров

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

    }

}

