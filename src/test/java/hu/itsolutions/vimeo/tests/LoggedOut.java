package hu.itsolutions.vimeo.tests;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import hu.itsolutions.vimeo.pages.SearchPage;

@ExtendWith(TextReportExtension.class)
public class LoggedOut {
    private SearchPage searchPage = new SearchPage();

    @BeforeAll
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @BeforeTest
    public void setup() {
        new ChromeOptions();
        searchPage.openUrl();

    }

    @AfterTest
    public void cleanup() {
        WebDriverRunner.getWebDriver().quit();
    }

    @Test
    public void checkNumberOfSearchResults() {
        searchPage.searchVideo("courage the cowardly dog");
        searchPage.checkPaginationExists();
    }

    @Test
    public void checkPaginationOnFirstPage() {
        searchPage.checkPaginationOnFirstPage();
    }

    @Test
    public void checkPaginationOnLastPage() {
        searchPage.checkPaginationOnLastPage();
    }


}
