package hu.itsolutions.vimeo.tests;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.qameta.allure.Description;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import hu.itsolutions.vimeo.pages.SearchPage;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.File;
import java.io.IOException;

import static hu.itsolutions.vimeo.Properties.URL_MAIN;
import static hu.itsolutions.vimeo.Properties.URL_WATCH;
import static hu.itsolutions.vimeo.Properties.SEARCH_STRING;

@ExtendWith(TextReportExtension.class)
public class LoggedOut {
    private SearchPage searchPage = new SearchPage();

    @BeforeTest
    public void setup() {
        searchPage.openUrl(URL_MAIN + URL_WATCH);
    }

    @AfterTest
    public void cleanup() {
        WebDriverRunner.getWebDriver().quit();
    }

    @AfterSuite
    @Step("Generate and open Allure report")
    public void generateReport() throws IOException, InterruptedException {
        String[] folders = {System.getProperty("user.dir"), "bin", "allure-2.23.1", "bin", "allure.bat"};
        String allurePath = String.join(File.separator, folders);
        Runtime.getRuntime().exec("cmd.exe /c " + allurePath + " generate --clean").waitFor();
        Runtime.getRuntime().exec("cmd.exe /c " + allurePath + " open");
    }

    @TestCaseId("IT-01")
    @Title("Check search feature of the application")
    @Description("If you initiate a search, and have numerous results, no more than 18 results should be listed on one page. Pagination should be used for the rest." +
            "On the First page in the Paginator no “PREV” button should be shown." +
            "In the Last page of the Paginator no “NEXT” button should be shown." +
            "In between both buttons should be displayed.")
    @Test
    public void checkNumberOfSearchResults() {
        searchPage.searchVideo(SEARCH_STRING)
                .checkPaginationExists()
                .checkPrevButtonOnFirstPage()
                .checkNextButtonOnLastPage(SEARCH_STRING)
                .checkPrevAndNextButtons(SEARCH_STRING);
    }


}
