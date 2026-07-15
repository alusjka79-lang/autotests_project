package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Page Object страницы Cart.
 *
 * Здесь находятся:
 * - локаторы элементов страницы корзины;
 * - методы работы с корзиной.
 * В тестах мы будем только вызывать методы этого класса, не используя driver.findElement() в тестах.
 */
public class CartPage {

    private WebDriver driver; // Объект браузера.
    private WebDriverWait wait; // Объект ожидания.

    // ЛОКАТОРЫ
    private By backpack = By.xpath("//div[text()='Sauce Labs Backpack']"); // Название товара Backpack.
    private By bikeLight = By.xpath("//div[text()='Sauce Labs Bike Light']"); // Название товара Bike Light.
    private By checkout = By.id("checkout"); // Кнопка Checkout.
    private By continueShopping = By.id("continue-shopping"); // Кнопка Continue Shopping.
    private By removeBackpack = By.id("remove-sauce-labs-backpack"); // Кнопка Remove у Backpack.
    private By removeBikeLight = By.id("remove-sauce-labs-bike-light"); // Кнопка Remove у Bike Light.

    /*
     * Конструктор класса.
     * При создании объекта CartPage передаем браузер и ожидание.
     */
    public CartPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver; // Сохраняем браузер.
        this.wait = wait; // Сохраняем ожидание.
    }

    /*
     * Проверка отображения Backpack в корзине.
     * Возвращает true, если товар найден.
     */
    public boolean isBackpackDisplayed(){
        Allure.step("Проверяем наличие Backpack в корзине");
        wait.until(ExpectedConditions.visibilityOfElementLocated(backpack));
        return driver.findElement(backpack).isDisplayed();
    }

    /*
     * Проверка отображения Bike Light в корзине.
     * Возвращает true, если товар найден.
     */
    public boolean isBikeLightDisplayed(){
        Allure.step("Проверяем наличие Bike Light в корзине");
        wait.until(ExpectedConditions.visibilityOfElementLocated(bikeLight));
        return driver.findElement(bikeLight).isDisplayed();
    }

    /*
     * Удаление Backpack из корзины.
     */
    public void removeBackpack(){
        Allure.step("Удаляем Backpack");
        wait.until(ExpectedConditions.elementToBeClickable(removeBackpack));
        driver.findElement(removeBackpack).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(backpack));
    }

    /*
     * Удаление Bike Light из корзины.
     */
    public void removeBikeLight(){
        Allure.step("Удаляем Bike Light");
        wait.until(ExpectedConditions.elementToBeClickable(removeBikeLight));
        driver.findElement(removeBikeLight).click();
    }

    /*
     * Переход к оформлению заказа.
     */
    public void checkout(){
        Allure.step("Нажимаем Checkout");
        wait.until(ExpectedConditions.elementToBeClickable(checkout));
        driver.findElement(checkout).click();
    }

    /*
     * Возврат на страницу товаров.
     */
    public void continueShopping(){
        Allure.step("Нажимаем Continue Shopping");
        wait.until(ExpectedConditions.elementToBeClickable(continueShopping));
        driver.findElement(continueShopping).click();
    }

    /*
     * Проверяет, что Bike Light больше нет в корзине.
     * Если элемента нет, метод вернет false.
     */
    public boolean isBikeLightPresent(){
        return driver.getPageSource().contains("Sauce Labs Bike Light");
    }

    /*
     * Проверяет, что Backpack больше нет в корзине.
     * Если элемента нет, метод вернет false.
     * ДОБАВЛЕНО: этот метод отсутствовал, а он нужен для тестов на удаление товара.
     */
    public boolean isBackpackPresent(){
        Allure.step("Проверяем отсутствие Backpack в корзине");
        return driver.getPageSource().contains("Sauce Labs Backpack");
    }

}