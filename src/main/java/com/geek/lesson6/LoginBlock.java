package com.geek.lesson6;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LoginBlock extends BaseView {

    public LoginBlock(WebDriver driver) {
        super(driver);
    }

    @FindAll({
            @FindBy(xpath = "//a"),
            @FindBy(xpath = "//div")
    })
    private List<WebElement> testElement;

    @FindBys({
            @FindBy(xpath = "//div[.='test']"),
            @FindBy(xpath = "//a")
    })
    private List<WebElement> testElement1;

    @FindBy(xpath = "//iframe[contains(@src,'rambler.ru/login')]")
    private WebElement loginFrame;

    public LoginBlock switchToLoginFrame() {
        driver.switchTo().frame(loginFrame);
        return this;
    }

    private final static String loginInputLocatorById = "login";
    @FindBy(id = loginInputLocatorById)
    private WebElement loginInput;

    public LoginBlock fillLoginInput(String login) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(loginInputLocatorById)));
        loginInput.sendKeys(login);
        return this;
    }

    @FindBy(id = "password")
    private WebElement passwordInput;

    public LoginBlock fillPasswordInput(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @FindBy(xpath = "//span[.='Войти']")
    private WebElement sumbitLoginButton;

    public MainPage submitLogin() {
        webDriverWait.until(d -> d.findElement(By.id(loginInputLocatorById)).getAttribute("value").contains("@rambler.ru"));
        sumbitLoginButton.click();
        return new MainPage(driver);
    }
}
