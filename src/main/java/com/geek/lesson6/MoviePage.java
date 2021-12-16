package com.geek.lesson6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class MoviePage extends BaseView {
    public MoviePage(WebDriver driver) {
        super(driver);
    }

    private final static String LIKE_BUTTON_XPATH_LOCATOR =
            "//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']";

    @FindBy(xpath = LIKE_BUTTON_XPATH_LOCATOR)
    private WebElement likeButton;

    public MoviePage likeFilm() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LIKE_BUTTON_XPATH_LOCATOR)));
        likeButton.click();
        return this;
    }

    private final static String ADDED_TO_FAVOURITES_XPATH_LOCATOR = "//div[.='Добавлено в избранное']";
    @FindBy(xpath = ADDED_TO_FAVOURITES_XPATH_LOCATOR)
    private WebElement addedToFavouritesElement;

    public MoviePage checkAddedToFavouritesElementIsDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ADDED_TO_FAVOURITES_XPATH_LOCATOR)));
        assertThat(addedToFavouritesElement, isDisplayed());
        return this;
    }
}
