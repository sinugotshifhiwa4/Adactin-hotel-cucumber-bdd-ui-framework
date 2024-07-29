package com.adactinhotel.stepDefinitions;

import com.adactinhotel.reusableComponents.LoggerConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertTrue;

public class BookHotelStepDef {

    private static final Logger logger = LoggerConfig.getLogger();
    BaseStepDef baseStep;

    public BookHotelStepDef(BaseStepDef baseStep){
        this.baseStep = baseStep;
    }

    @Given("the user is on the search hotel page")
    public void theUserIsOnTheSearchHotelPage() {
        boolean isWelcomeTextVisible = baseStep.loginPage.verifyWelcomeText();
        assertTrue(isWelcomeTextVisible);
        logger.info("User is navigated to the search hotel page");
    }

    @When("the user fills out all fields on the search hotel page and clicks the search button")
    public void theUserFillsOutAllFieldsOnTheSearchHotelPageAndClicksTheSearchButton() {
        logger.info("User fills out all fields on the search hotel page");
        baseStep.searchHotelPage.searchForHotel(
                "Paris",
                "Hotel Sunshine",
                "Deluxe",
                "3 - Three",
                "10/08/20204",
                "15/08/2024",
                "2 - Two",
                "1 - One"
        );
        logger.info("User fills out all fields on the search hotel page");
    }

    @Then("the user is navigated to the select hotel page")
    public void theUserIsNavigatedToTheSelectHotelPage() {
        logger.info("User is navigated to the select hotel page");
        boolean isContinueButtonVisible = baseStep.searchHotelPage.verifyContinueButtonIsVisible();
        assertTrue(isContinueButtonVisible);
    }

    @And("the user selects the hotel they want and clicks the continue button")
    public void theUserSelectsTheHotelTheyWantAndClicksTheContinueButton() {
    }

    @Then("the user is navigated to the book hotel page")
    public void theUserIsNavigatedToTheBookHotelPage() {
    }

    @And("the user fills out all billing information and clicks the book now button")
    public void theUserFillsOutAllBillingInformationAndClicksTheBookNowButton() {
    }

    @Then("the user has successfully booked the hotel and can see the order number")
    public void theUserHasSuccessfullyBookedTheHotelAndCanSeeTheOrderNumber() {
    }
}
