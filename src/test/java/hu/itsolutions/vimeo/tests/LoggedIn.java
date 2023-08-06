package hu.itsolutions.vimeo.tests;

import com.codeborne.selenide.WebDriverRunner;
import hu.itsolutions.vimeo.pages.SearchPage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import hu.itsolutions.vimeo.pages.HomePage;
import hu.itsolutions.vimeo.pages.LogInPage;

public class LoggedIn {

    private HomePage homePage = new HomePage();
    private LogInPage logInPage = new LogInPage();
    private SearchPage searchPage = new SearchPage();

    @BeforeTest
    public void setup() {
        new ChromeOptions();
        homePage.logIn();
        logInPage.logIn();
        logInPage.openUrl();
    }

    @AfterTest
    public void cleanup() {
        WebDriverRunner.getWebDriver().quit();
    }

    @Test
    public void checkNumberOfSearchResults() {
        searchPage.searchVideo("courage the cowardly dog");
    }
}
