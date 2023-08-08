package hu.itsolutions.vimeo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.CollectionCondition.size;

import static hu.itsolutions.vimeo.Properties.URL_MAIN;
import static hu.itsolutions.vimeo.Properties.RESULTS_ON_ONE_PAGE;

public class SearchPage {
    SelenideElement textFieldSearch = $(byId("topnav-search"));
    SelenideElement resultsCount = $(".results_count");
    SelenideElement paginationPanel = $(byId("pagination"));
    SelenideElement buttonPaginationPrev = $(byText("Prev"));
    SelenideElement buttonPaginationNext = $(byText("Next"));


    @Step("Open url: {0}")
    public SearchPage openUrl(String url) {
        open(url);
        return this;
    }

    @Step("Search for \"{0}\" and press ENTER")
    private SearchPage insertSearch(String searchString) {
        textFieldSearch.append(searchString).pressEnter();
        return new SearchPage();
    }

    @Step("Check if number of results are exactly 18")
    private void getNumberOfResultsInOnePage() {
        $$(".iris-annotation-layer").shouldHave(size(RESULTS_ON_ONE_PAGE));
    }

    @Step("Check if pagination is available")
    public SearchPage checkPaginationExists() {
        if (getSearchResults() > RESULTS_ON_ONE_PAGE) {
            paginationPanel.should(exist);
        }
        return this;
    }

    @Step("Search for video")
    public SearchPage searchVideo(String text) {
        insertSearch(text).getNumberOfResultsInOnePage();
        return this;
    }

    @Step("\"PREV\" button should be visible")
    public SearchPage checkPrevButtonOnFirstPage() {
        buttonPaginationPrev.shouldNot(exist);
        buttonPaginationNext.should(exist);
        return this;
    }

    @Step("\"NEXT\" button should be visible")
    public SearchPage checkNextButtonOnLastPage(String searchString) {
        int lastPageNumber = openLastPageOfResults(searchString);
        if (lastPageNumber > 1) {
            buttonPaginationNext.shouldNot(exist);
            buttonPaginationPrev.should(exist);
        }
        return this;
    }

    @Step("Both \"PREV\" and \"NEXT\" buttons should be visible")
    public SearchPage checkPrevAndNextButtons(String searchString) {
        if (getSearchResults() > 36){
            openPageOfSearchResult(2, searchString);
            buttonPaginationNext.should(exist);
            buttonPaginationPrev.should(exist);
        }
        return this;
    }

    private int getSearchResults() {
        String text = resultsCount.getText();
        return Integer.parseInt(text.split(" ")[0]);
    }

    private int openLastPageOfResults(String searchString) {
        int lastPageNumber = (getSearchResults() / RESULTS_ON_ONE_PAGE) + 1;
        openPageOfSearchResult(lastPageNumber, searchString);
        return lastPageNumber;
    }

    private void openPageOfSearchResult(int pageNumber, String searchString){
        open(URL_MAIN + "search/page:" + pageNumber + "?q=" + searchString.replace(" ", "+"));
    }

}
