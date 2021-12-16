package com.geek.lesson5.hw4;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CalcAreaTests {
    private final static Integer LONG_SLEEP = 20;
    private final static String GOOGLE_CAPTCHA_ASSERTION = "if (grecaptcha.getResponse().length == 0){throw \"You did not pass the captcha!\";}";

    @Test
    void successSquareCalculation() throws Exception {
        Assertions.assertEquals(TriangleArea.calcArea(3, 4, 5), 6);
        Assertions.assertTrue(Math.abs(TriangleArea.calcArea(3, 4, 5) - 6) < 0.0001);
        Assert.assertEquals(6, TriangleArea.calcArea(3, 4, 5), 0.0002);
    }

    @Test
    void negativeTriangleTest() {
        Assertions.assertThrows(Exception.class, () -> TriangleArea.calcArea(-3, 4, 5));
    }

    //метод проверки успешности прохождения капчи
    private static void sendCaptcha(WebDriver driver) throws InterruptedException {
        System.out.println("Если Вы сейчас не разгадаете капчу, тест провалится");
        Thread.sleep(LONG_SLEEP);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(LONG_SLEEP)).until(
                    ExpectedConditions.javaScriptThrowsNoExceptions(GOOGLE_CAPTCHA_ASSERTION)
            );
        } catch (TimeoutException e) {
            driver.quit();
            throw new RuntimeException("Вам надо было разгадать капчу, но Вы этого не сделали");
        }
        System.out.println("Вы успешно разгадали капчу");
    }
}
