package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By username = By.id("user-name");
    private By password = By.cssSelector("[data-test='password']");
    private By loginButton = By.xpath("//input[@id='login-button']");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String pass){
        Allure.step("Авторизация пользователя");
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginButton).click();
    }

    public String getErrorText(){
        Allure.step("Получение текста ошибки");
        return driver.findElement(errorMessage).getText();
    }
}
