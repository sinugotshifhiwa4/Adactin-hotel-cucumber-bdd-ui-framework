package com.adactinhotel.pages;

import com.adactinhotel.reusableComponents.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends WebActions {

    WebDriver driver;

    @FindBy(css = "#username")
    private WebElement usernameTextBox;

    @FindBy(css = "#password")
    private WebElement passwordTextBox;

    @FindBy(css = "#login")
    private WebElement loginButton;

    @FindBy(css = "#username_show")
    private WebElement welcomeTextMessage;

    @FindBy(css = "div[class='auth_error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillUsername(String username) {
        clearElement(usernameTextBox, "UsernameField");
        fillElement(usernameTextBox, "UsernameField", username);
    }

    public void fillPassword(String password) {
        clearElement(passwordTextBox, "PasswordField");
        fillElement(passwordTextBox, "PasswordField", password);
    }

    public void clickLoginButton() {
        clickElement(loginButton, "LoginButton");
    }

    public void loginToAdactinHotel(String username, String password) {
        fillUsername(username);
        fillPassword(password);
        clickLoginButton();
    }

    public WebElement usernameElement(){
        return usernameTextBox;
    }

    public WebElement passwordElement(){
        return passwordTextBox;
    }

    public boolean verifyWelcomeText(){
        return verifyElementIsVisible(welcomeTextMessage, "WelcomeMessage");
    }

    public boolean verifyErrorMessageIsVisible(){
        return verifyElementIsVisible(errorMessage, "ErrorMessage");
    }
}
