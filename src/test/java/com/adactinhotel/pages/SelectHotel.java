package com.adactinhotel.pages;

import com.adactinhotel.reusableComponents.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectHotel extends WebActions {

    WebDriver driver;

    @FindBy(css = "[type='radio']")
    private WebElement radioButton;

    @FindBy(css = "#continue")
    private WebElement continueButton;

    @FindBy(css = "#book_now")
    private WebElement bookNowButton;

    public SelectHotel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickToSelectHotel() {
        clickElement(radioButton, "radioButton");
    }

    public void clickContinueButton() {
        clickElement(continueButton, "continueButton");
    }

    public boolean verifyBookNowButton(){
        return verifyElementIsVisible(bookNowButton, "bookNowButton");
    }

}
