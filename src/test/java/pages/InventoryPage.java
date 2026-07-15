package pages;

import io.qameta.allure.Allure;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

// Импорт класса Select. Используется для работы с выпадающим списком сортировки.

import org.openqa.selenium.support.ui.Select;

// Импорт ожиданий.

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

/*

 * Page Object страницы Products (Inventory).

 *

 * Здесь находятся все элементы страницы

 * и методы для работы с ними.

 */

public class InventoryPage {

    private WebDriver driver; // Сохраняем браузер.

    private WebDriverWait wait; // Сохраняем объект ожидания.

    // ЛОКАТОРЫ

    private By backpack = By.id("add-to-cart-sauce-labs-backpack"); // Кнопка добавления рюкзака.

    private By bikeLight = By.id("add-to-cart-sauce-labs-bike-light");

    private By cartButton = By.className("shopping_cart_link"); // Значок корзины.

    private By cartBadge = By.className("shopping_cart_badge"); // Счетчик товаров в корзине.

    private By menuButton = By.id("react-burger-menu-btn"); // Кнопка открытия бокового меню.

    private By logout = By.id("logout_sidebar_link"); // Кнопка Logout.

    private By title = By.className("title"); // Заголовок страницы Products.

    private By sortDropdown = By.className("product_sort_container"); // Выпадающий список сортировки.

    public InventoryPage(WebDriver driver, WebDriverWait wait){ // Конструктор класса.

        this.driver = driver; // Сохраняем браузер.

        this.wait = wait; // Сохраняем ожидание.

    }

    /*

     * Добавление рюкзака в корзину.

     */

    public void addBackpackToCart(){

        Allure.step("Добавление Sauce Labs Backpack в корзину");

        wait.until(ExpectedConditions.elementToBeClickable(backpack)); // Ждем появления кнопки.

        driver.findElement(backpack).click(); // Нажимаем кнопку Add to cart.

    }

    /*

     * Добавление Bike Light в корзину.

     */

    public void addBikeLightToCart(){

        Allure.step("Добавление фонаря в корзину"); // Добавляем шаг в отчет Allure.

        wait.until(ExpectedConditions.elementToBeClickable(bikeLight)); // Ждем появления кнопки.

        driver.findElement(bikeLight).click(); // Нажимаем кнопку

    }

    /*

     * Открытие корзины.

     */

    public void openCart(){

        Allure.step("Открытие корзины");

        wait.until(ExpectedConditions.elementToBeClickable(cartButton));

        driver.findElement(cartButton).click();

    }

    /*

     * Получение количества товаров в корзине.

     */

    public String getCartBadge(){

        Allure.step("Получение количества товаров в корзине");

        wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)); // Ждем появления бейджа.

        return driver.findElement(cartBadge).getText(); // Получаем текст с бейджа корзины, чтобы узнать колличество товара

    }

    /*

     * Выход из аккаунта.

     */

    public void logout(){

        Allure.step("Выход из аккаунта");

        driver.findElement(menuButton).click();

        wait.until(ExpectedConditions.elementToBeClickable(logout)); // Ждем появления кнопки.

        driver.findElement(logout).click();

    }

    /*

     * Получение названия страницы.

     */

    public String getTitle(){

        return driver.findElement(title).getText();

    }

    /*

     * Проверка Url, что пользователь находится на странице Products (inventory).

     */

    public boolean isInventoryPageDisplayed(){

        return driver.getCurrentUrl().contains("inventory");

    }

}

