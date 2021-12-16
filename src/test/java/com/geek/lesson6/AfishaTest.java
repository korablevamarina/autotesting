package com.geek.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AfishaTest {
    WebDriver driver;
    MainPage mainPage;
    LoginBlock loginBlock;
    private final static String AFISHA_BASE_URL = "https://afisha.ru";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        loginBlock = new LoginBlock(driver);
        driver.get(AFISHA_BASE_URL);
    }

    @Test
    void likeRandomMovieTest() throws InterruptedException {
        //mainPage.loginButton.click();
        //
        //driver.switchTo().frame(loginBlock.loginFrame);
        //loginBlock.loginInput.sendKeys("spartalex1993");
        //loginBlock.passwordInput.sendKeys("Test4test");

        new MainPage(driver).clickLoginButton();

        new LoginBlock(driver)
                .switchToLoginFrame()
                .fillLoginInput("spartalex1993")
                .fillPasswordInput("Test4test")
                .submitLogin();
        Thread.sleep(20000);

        new MainPage(driver)
                .clickToMovieByName("Матрица");

        new MoviePage(driver)
                .likeFilm()
                .checkAddedToFavouritesElementIsDisplayed();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
