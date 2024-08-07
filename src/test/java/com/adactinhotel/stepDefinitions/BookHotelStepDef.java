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
        System.out.println("Continue button is visible" +  isContinueButtonVisible);
        logger.info("User is navigated to the select hotel page");
    }

    @And("the user selects the hotel they want and clicks the continue button")
    public void theUserSelectsTheHotelTheyWantAndClicksTheContinueButton() {
        logger.info("user is selecting hotel and clicking continue button");
        baseStep.selectHotel.clickToSelectHotel();
        baseStep.selectHotel.clickContinueButton();
        logger.info("user is selected hotel and clicked continue button");
    }

    @Then("the user is navigated to the book hotel page")
    public void theUserIsNavigatedToTheBookHotelPage() {
        logger.info("User is navigated to the book hotel page");
        boolean isVisible = baseStep.selectHotel.verifyBookNowButton();
        assertTrue(isVisible);
        System.out.println("Book now button is visible" +  isVisible);
        logger.info("User is navigated to the book hotel page");
    }

    @And("the user fills out all billing information and clicks the book now button")
    public void theUserFillsOutAllBillingInformationAndClicksTheBookNowButton() {
        logger.info("filling all billing information");
        baseStep.bookHotelPage.fillBillingInformation(
                "Nathan",
                "Ake",
                "New St, Marshalltown, Johannesburg, 2001",
                "5425233430109903",
                "Master Card",
                "April",
                "2026",
                "123"
        );
        logger.info("User fills out all billing information");
    }

    @Then("the user has successfully booked the hotel and can see the order number")
    public void theUserHasSuccessfullyBookedTheHotelAndCanSeeTheOrderNumber() {
        logger.info("User has successfully booked the hotel and can see the hotel booking order");

        baseStep.bookHotelPage.checkIfLastNameTextBoxIsEmpty();

        boolean isVisible = baseStep.bookHotelPage.verifyOrderNumber();
        assertTrue(isVisible);
        baseStep.bookHotelPage.getOrderNumberAndSaveToFile();
        logger.info("booking was successful and order number was generated and saved to file");
    }
}
