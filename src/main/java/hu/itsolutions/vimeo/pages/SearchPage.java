package hu.itsolutions.vimeo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.CollectionCondition.size;

public class SearchPage {
    public static final Integer RESULTS_ON_ONE_PAGE = 18;
    SelenideElement textFieldSearch = $(byId("topnav-search"));
    SelenideElement resultsCount = $(".results_count");
    SelenideElement paginationPanel = $(byId("pagination"));
    SelenideElement buttonPaginationPrev = $(byText("Prev"));
    SelenideElement buttonPaginationNext = $(byText("Next"));



    public SearchPage openUrl() {
        open("https://vimeo.com/watch");
        return new SearchPage();
    }

    @Step("Search for \"{0}\"")
    public SearchPage insertSearch(String text) {
        textFieldSearch.append(text).pressEnter();
        return new SearchPage();
    }

    private int getSearchResults (){
        String text;
        text = resultsCount.getText();
        String[] parts = text.split(" ");
        return Integer.parseInt(parts[0]);
    }

    public void getNumberOfResultsInOnePage(){
        $$(".iris-annotation-layer").shouldHave(size(RESULTS_ON_ONE_PAGE));
    }

    public void checkPaginationExists(){
        if ( getSearchResults() > RESULTS_ON_ONE_PAGE ){
            paginationPanel.should(exist);
        }
    }

    @Step("Search video")
    public void searchVideo(String text) {
        insertSearch(text);
        getNumberOfResultsInOnePage();
    }

    public void checkPaginationOnFirstPage(){
        buttonPaginationPrev.shouldNot(exist);
        buttonPaginationNext.should(exist);
    }

    public void checkPaginationOnLastPage(){
        int pages = (getSearchResults()/RESULTS_ON_ONE_PAGE)+1;
        String stringPages = Integer.toString(pages);
        open("https://vimeo.com/search/page:"+stringPages+"?q=courage+the+cowardly+dog");
        buttonPaginationNext.shouldNot(exist);
        buttonPaginationPrev.should(exist);
    }



}
