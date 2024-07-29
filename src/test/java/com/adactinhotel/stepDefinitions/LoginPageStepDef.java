package com.adactinhotel.stepDefinitions;

import com.adactinhotel.reusableComponents.LoggerConfig;
import com.adactinhotel.reusableComponents.PropertiesConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertTrue;

public class LoginPageStepDef {

    private static final Logger logger = LoggerConfig.getLogger();
    BaseStepDef baseStep;

    public LoginPageStepDef(BaseStepDef baseStep){
        this.baseStep = baseStep;
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        logger.info("Login page opened");
    }

    @When("the user enters valid username into the username field")
    public void theUserEntersValidUsernameIntoTheUsernameField() {
        logger.info("Entering valid username into the username field");
        baseStep.loginPage.fillUsername(PropertiesConfig.getPropertyKey("username"));
        logger.info("username entered successfully");
    }

    @And("the user enters valid password into the password field")
    public void theUserEntersValidPasswordIntoThePasswordField() {
        logger.info("Entering valid password into the password field");
        baseStep.loginPage.fillPassword(PropertiesConfig.getPropertyKey("password"));
        logger.info("password entered successfully");
    }

    @And("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        logger.info("Clicking login button");
        baseStep.loginPage.clickLoginButton();
        logger.info("login button clicked");
    }

    @Then("the user should logged in successfully and navigated to the search hotel page")
    public void theUserShouldLoggedInSuccessfullyAndNavigatedToTheSearchHotelPage() {
        logger.info("validating login was successful");
        boolean isWelcomeTextVisible = baseStep.loginPage.verifyWelcomeText();
        assertTrue(isWelcomeTextVisible);
        logger.info("User is navigated to the search hotel page");
    }

    @When("the user enters an invalid username into the username field")
    public void theUserEntersAnInvalidUsernameIntoTheUsernameField() {
        logger.info("Entering an invalid username into the username field");
        baseStep.loginPage.fillUsername(PropertiesConfig.getPropertyKey("invalidUsername"));
        logger.info("invalid username entered successfully");
    }

    @And("the user enters an invalid password into the password field")
    public void theUserEntersAnInvalidPasswordIntoThePasswordField() {
        logger.info("Entering an invalid password into the password field");
        baseStep.loginPage.fillPassword(PropertiesConfig.getPropertyKey("invalidPassword"));
        logger.info("invalid password entered successfully");
    }

    @Then("the user should get an error message saying incorrect credentials")
    public void theUserShouldGetAnErrorMessageSayingIncorrectCredentials() {
        logger.info("validating login failed");
        boolean isErrorMessageVisible = baseStep.loginPage.verifyErrorMessageIsVisible();
        assertTrue(isErrorMessageVisible);
        logger.info("login failed. error message is displayed");
    }
}
