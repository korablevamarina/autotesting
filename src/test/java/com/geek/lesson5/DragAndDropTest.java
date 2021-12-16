package com.geek.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class DragAndDropTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String BASE_URL = "https://crossbrowsertesting.github.io/drag-and-drop.html";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get(BASE_URL);
    }

    @Test
    void dragAndDropTest() throws InterruptedException {
        actions.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable")))
                .build()
                .perform();
        assertThat(driver.findElement(By.xpath("//div[@id='droppable']/p")), hasText("Dropped!"));

        ((JavascriptExecutor)driver).executeScript("alert('Test!')");
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
        Thread.sleep(5000);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
