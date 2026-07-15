package pages;

import io.qameta.allure.Allure;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

/*

 * Page Object страницы Checkout.

 * Здесь происходит ввод данных пользователя и завершение оформления заказа.

 */

public class CheckoutPage {

    private WebDriver driver;

    private WebDriverWait wait;

    // ЛОКАТОРЫ

    private By firstNameField = By.id("first-name"); // Поле First Name.

    private By lastNameField = By.id("last-name"); // Поле Last Name.

    private By postalCodeField = By.id("postal-code"); // Поле Postal Code.

    private By continueButton = By.id("continue"); // Кнопка Continue.

    private By finishButton = By.id("finish"); // Кнопка Finish.

    // Конструктор класса.

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {

        this.driver = driver; // Сохраняем браузер.

        this.wait = wait; // Сохраняем ожидание.

    }

    /*

     * Заполнение формы checkout данными пользователя.

     * Сейчас метод универсальный: можно передавать разные данные.

     */

    public void fillInformation(String firstName, String lastName, String postalCode) {

        Allure.step("Заполнение формы checkout данными пользователя");

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)); // Ждем поле First Name.

        driver.findElement(firstNameField).sendKeys(firstName); // Вводим имя.

        driver.findElement(lastNameField).sendKeys(lastName); // Вводим фамилию.

        driver.findElement(postalCodeField).sendKeys(postalCode); // Вводим индекс.

        driver.findElement(continueButton).click(); // Нажимаем Continue.

    }

    /*

     * Завершение заказа.

     */

    public void finishOrder() {

        Allure.step("Завершение заказа");

        wait.until(ExpectedConditions.elementToBeClickable(finishButton)); // Ждем кнопку Finish.

        driver.findElement(finishButton).click(); // Нажимаем Finish.

    }

}

