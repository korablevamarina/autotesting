package com.geek.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BaseView {

    @FindBy(xpath = "//button[.='Войти']")
    WebElement loginButton;

    public MainPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    @FindBy(xpath = "//a[contains(@href, 'movie') and contains(@data-test,'LINK')]/ancestor::div[@data-test='ITEM']")
    private List<WebElement> moviesList;

    public MoviePage clickToMovieByName(String filmName) {
        moviesList.stream().filter(m -> m.getText().contains(filmName)).findFirst().get().click();
        return new MoviePage(driver);
    }

    public MainPage(WebDriver driver) {
        super(driver);
    }
}
