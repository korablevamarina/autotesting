package com.geek.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class SetupBrowserExamples {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //запустить браузер без интерфейса
        //options.addArguments("--headless");
        //заблокировать нотификации
        options.addArguments("--disable-notifications");
        //"прикинуться" поисковым роботом
        options.addArguments("user-agent=Googlebot/2.1 (+http://www.google.com/bot.html)");
        //Настройки передаем в конструктор драйвера, если это нужно
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://google.com");
        Thread.sleep(5000);
        //driver.quit();

        //Выполнить js скрипт на странице
        ((JavascriptExecutor)driver).executeScript("window.open()");
        //Получили список дескрипторов открытых окон (дескриптор-просто набор символов, уникальный для каждой вкладки)
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        Thread.sleep(3000);
        //Передаем дескриптор первой (индексация с 0) вкладки, чтобы перейти в нее
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(3000);
        driver.quit();
    }
}
