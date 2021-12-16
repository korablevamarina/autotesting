package com.geek.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class AfishaTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String AFISHA_BASE_URL = "https://www.afisha.ru/";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get(AFISHA_BASE_URL);
    }

    @Test
    void likeRandomFilmTest() throws InterruptedException {
        login();

        webDriverWait.until(d -> d.findElements(
                By.xpath("//a[contains(@href, 'movie') and contains(@data-test,'LINK')]/ancestor::div[@data-test='ITEM']")).size() > 0);
        List<WebElement> filmsList = driver.findElements(
                By.xpath("//a[contains(@href, 'movie') and contains(@data-test,'LINK')]/ancestor::div[@data-test='ITEM']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                filmsList.stream().filter(f -> f.getText().contains("Gucci")).findFirst().get());
        filmsList.stream().filter(f -> f.getText().contains("Gucci")).findFirst().get().click();
        //filmsList.stream().filter(f -> f.getCssValue("color").contains("Gucci")).findFirst().get().click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']")));
        driver.findElement(By.xpath("//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Добавлено в избранное']")));
        Assertions.assertEquals(driver.findElement(By.xpath("//div[.='Добавлено в избранное']")).isDisplayed(), true);
        assertThat(driver.findElement(By.xpath("//div[.='Добавлено в избранное']")), isDisplayed());
    }

    @Test
    void hoverMenuTest() {
        actions.moveToElement(driver.findElement(By.xpath("//div[@data-test='HEADER-MENU']//a[@href='/msk/cinema/']")))
                .build()
                .perform();
        driver.findElement(By.xpath("//div[@data-test='SUGGEST']//a[.='IMAX кинотеатры']")).click();
        assertThat(driver.getCurrentUrl(), containsString("imax"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    void login() throws InterruptedException {
        driver.findElement(By.xpath("//button[.='Войти']")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'rambler.ru/login')]")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        driver.findElement(By.id("login")).sendKeys("spartalex1993");
        driver.findElement(By.id("password")).sendKeys("Test4test");
        webDriverWait.until(d -> d.findElement(By.id("login")).getAttribute("value").contains("@rambler.ru"));
        driver.findElement(By.xpath("//span[.='Войти']")).click();
        Thread.sleep(20000);
    }
}
