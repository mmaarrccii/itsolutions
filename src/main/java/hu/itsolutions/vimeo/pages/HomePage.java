package hu.itsolutions.vimeo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomePage {
    SelenideElement buttonLogIn = $(byText("Log in"));

    public HomePage openUrl() {
        open("https://vimeo.com/");
        return new HomePage();
    }

    @Step("Click log in button")
    public HomePage clickButtonLogIn() {
        buttonLogIn.click();
        return new HomePage();
    }

    @Step("Log in to Vimeo")
    public void logIn() {
        openUrl();
        clickButtonLogIn();
    }
}
