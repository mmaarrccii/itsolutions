package hu.itsolutions.vimeo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LogInPage {

    SelenideElement txtBoxEmail = $(byId("login_email"));
    SelenideElement txtBoxPassword = $(byId("password"));
    SelenideElement buttonLogIn = $(byText("Log in with email"));

    public LogInPage openUrl() {
        open("https://vimeo.com/watch");
        return new LogInPage();
    }

    public LogInPage loginAccount(String email, String password) {
        validatePage();
        txtBoxEmail.setValue(email);
        txtBoxPassword.setValue(password);
        txtBoxPassword.pressEnter();
        return new LogInPage();
    }

    public void validatePage() {
        txtBoxEmail.should(exist).shouldBe(visible).shouldBe(enabled);
        txtBoxPassword.should(exist).shouldBe(visible).shouldBe(enabled);
        buttonLogIn.should(exist).shouldBe(visible).shouldBe(enabled);
    }

    public void clickButtonLogIn() {
        String email = "theitsolutions6@gmail.com";
        String password = "TheITSolutions.66";
        loginAccount (email, password);
    }

    @Step("Log in")
    public LogInPage logIn() {
        clickButtonLogIn();
        return new LogInPage();
    }

}
