package com.geek.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DiaryTests {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://diary.ru/user/login");
        //ищем элемент на странице
        WebElement loginField = driver.findElement(By.id("loginform-username"));
        loginField.sendKeys("spartalex");
        driver.findElement(By.id("loginform-password")).sendKeys("123456");

        //Переход в iframe
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        //Ищем элемент по xpath
        driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
        driver.switchTo().parentFrame();
        //driver.findElement(By.id("login_btn")).click();

        //Подкладываем куку в браузер, с которой мы авторизованы
        Cookie cookie = new Cookie("_identity_", "83668234c30812b14c46bac1deda1a240770255504032650b424a75038c00b3aa%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3545507%2C%22E_QJqRaNdBrepyeVN7uNXi5Dz9tjGpfX%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();

        String postTitle = "test123123";
        driver.findElement(By.xpath("//a[@title='Новая запись']")).click();
        driver.findElement(By.id("postTitle")).sendKeys(postTitle);
        driver.switchTo().frame(driver.findElement(By.id("message_ifr")));
        driver.findElement(By.id("tinymce")).sendKeys("testdfgsdf");
        driver.switchTo().parentFrame();
        Thread.sleep(3000);
        //Синтетический пример с ожиданиями элементов
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        //Ниже вариант для 4 селениума (по-другому передается время ожидания)
        //WebDriverWait webDriverWaitSelenium4 = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Так можно явно указать, как часто будем опрашивать элемент, перешел ли он в нужное состояние (например стал видимым)
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10)) //тут максимальное время ожидания
                .pollingEvery(Duration.ofMillis(10)) //тут как часто опрашиваем
                .ignoring(NoSuchElementException.class);

        //ExpectedConditions. - список заготовленных методов для ожидания
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div"))));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div")));
        driver.findElement(By.xpath("//input[@value='Опубликовать']")).click();
        driver.findElement(By.xpath(String.format("//a[text()='%s']/following-sibling::span//span[@class='i-cross']", postTitle))).click();
        Thread.sleep(20000);

        driver.quit();
    }
}
