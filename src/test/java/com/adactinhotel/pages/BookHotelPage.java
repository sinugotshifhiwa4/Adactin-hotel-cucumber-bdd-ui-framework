package com.adactinhotel.pages;

import com.adactinhotel.reusableComponents.PropertiesConfig;
import com.adactinhotel.reusableComponents.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookHotelPage extends WebActions {

    WebDriver driver;
    String selectByVisibleText = PropertiesConfig.getPropertyKey("selectByVisibleText");

    @FindBy(css = "#first_name")
    private WebElement firstNameTextBox;

    @FindBy(css = "#last_name")
    private WebElement lastNameTextBox;

    @FindBy(css = "#address")
    private WebElement billingAddressTextBox;

    @FindBy(css = "#cc_num")
    private WebElement creditCardNumberTextBox;

    @FindBy(css = "#cc_type")
    private WebElement creditCardTypeSelector;

    @FindBy(css = "#cc_exp_month")
    private WebElement monthSelector;

    @FindBy(css = "#cc_exp_year")
    private WebElement yearSelector;

    @FindBy(css = "#cc_cvv")
    private WebElement cvvNumberTextBox;

    @FindBy(css = "#book_now")
    private WebElement bookNowButton;

    @FindBy(css = "#order_no")
    private WebElement orderNumber;

    @FindBy(css = "#last_name")
    private WebElement lastNameOnConfirmation;

    public BookHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillFirstName(String firstName) {
        fillElement(firstNameTextBox, "firstName", firstName);
    }

    public void fillLastName(String lastName) {
        fillElement(lastNameTextBox, "lastName", lastName);
    }

    public void fillBillingAddress(String address) {
        fillElement(billingAddressTextBox, "address", address);
    }

    public void fillCreditCardNumber(String number) {
        fillElement(creditCardNumberTextBox, "number", number);
    }

    public void selectCreditCardType(String creditCardType) {
        selectElementFromDropDown(creditCardTypeSelector, "creditCardType", selectByVisibleText, creditCardType);
    }

    public void selectMonth(String month) {
        selectElementFromDropDown(monthSelector, "month", selectByVisibleText, month);
    }

    public void selectYear(String year) {
        selectElementFromDropDown(yearSelector, "year", selectByVisibleText, year);
    }

    public void fillCvvNumber(String number) {
        fillElement(cvvNumberTextBox, "cvvNumber", number);
    }

    public void clickBookNowButton(){
        clickElement(bookNowButton, "bookNowButton");
    }

    public void fillBillingInformation(
            String firstName,
            String lastName,
            String billingAddress,
            String creditCardNumber,
            String creditCardType,
            String month,
            String year,
            String cvvNumber
    ){
        fillFirstName(firstName);
        fillLastName(lastName);
        fillBillingAddress(billingAddress);
        fillCreditCardNumber(creditCardNumber);
        selectCreditCardType(creditCardType);
        selectMonth(month);
        selectYear(year);
        fillCvvNumber(cvvNumber);
        clickBookNowButton();
    }

    public void getOrderNumberAndSaveToFile(){
        String _orderNumber = getTextFromElement(orderNumber, "orderNumber");
        writeTextToFile(_orderNumber);
    }

    public void readOrderNumberSaved(){
        String _orderNumber = readTextFromFile();
        if(_orderNumber != null){
            System.out.println("Order number save is: " + _orderNumber);
        }
    }

    public boolean verifyOrderNumber(){
        return verifyElementIsVisible(orderNumber, "orderNumber");
    }

    public void checkIfLastNameTextBoxIsEmpty(){
        getTextFromElement(lastNameOnConfirmation, "lastName");
    }
}
