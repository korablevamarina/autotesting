package com.geek.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumStart {
    public static void main(String[] args) throws InterruptedException {
        //Регистрация драйвера, "говорим" джаве, как найти драйвер (на windows не забудьте добавить .exe в конце)
        //сначала этот драйвер надо скачать из https://chromedriver.chromium.org/downloads
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        //Переходим на указанную страницу
        driver.get("https://google.com");
        Thread.sleep(5000);
        //Закрыть весь браузер
        driver.quit();

        //Регистрация драйвера с помощью webdriverManager, который скачает нужный драйвер за нас
        WebDriverManager.firefoxdriver().setup();
        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.get("https://ya.ru");
        //Закрыть текущую вкладку
        firefoxDriver.close();
    }
}
