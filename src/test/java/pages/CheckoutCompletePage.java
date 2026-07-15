package pages;

import io.qameta.allure.Allure;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

/*

 * Page Object страницы завершения заказа (Checkout Complete).

 * Эта страница появляется после успешного оформления заказа.

 * Здесь мы проверяем сообщение об успехе.

 */

public class CheckoutCompletePage {

    private WebDriver driver;

    private WebDriverWait wait;

    // ЛОКАТОРЫ

    private By successMessage = By.className("complete-header"); // Заголовок успешного оформления заказа.

    // Конструктор класса.

    public CheckoutCompletePage(WebDriver driver, WebDriverWait wait) {

        this.driver = driver; // Сохраняем драйвер браузера.

        this.wait = wait; // Сохраняем ожидание.

    }

    /*

     * Получение текста успешного завершения заказа.

     * Используется в assert проверках.

     */

    public String getSuccessMessage() {

        Allure.step("Проверка сообщения об успешном заказе");

        // Ждём появления заголовка успеха.

        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));

        // Возвращаем текст сообщения.

        return driver.findElement(successMessage).getText();

    }

}

