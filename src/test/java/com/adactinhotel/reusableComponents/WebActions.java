package com.adactinhotel.reusableComponents;

import com.adactinhotel.utils.BrowserDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class WebActions {

    String filePath = PropertiesConfig.getPropertyKey("orderNumberFilePath");

    public Wait<WebDriver> initFluentWait(){

        return new FluentWait<>(BrowserDriverFactory.getInstance().getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(WebDriverException.class)
                .ignoring(RuntimeException.class);
    }

    public void fillElement(WebElement element, String fieldName, String value) {

        try{
            Wait<WebDriver> wait = initFluentWait();
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(value);

        } catch (Exception e){
            CustomException.handleException("FillElement" + fieldName, e);
        }
    }

    public void clickElement(WebElement element, String fieldName) {

        try{
            Wait<WebDriver> wait = initFluentWait();
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();

        } catch (Exception e){
            CustomException.handleException("ClickElement" + fieldName, e);
        }
    }

    public void clearElement(WebElement element, String fieldName) {

        try{
            Wait<WebDriver> wait = initFluentWait();
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();

        } catch (Exception e){
            CustomException.handleException("ClearElement" + fieldName, e);
        }
    }

    public void selectElementFromDropDown(WebElement element, String fieldName, String selectMethod, Object selectDropDownValue) {

        try{
            Select select = new Select(element);

            switch (selectMethod.toLowerCase()){

                case "selectbyindex":
                    select.selectByIndex((Integer) selectDropDownValue);
                    break;

                case "selectbyvalue":
                    select.selectByValue((String) selectDropDownValue);
                    break;

                case "selectbyvisibletext":
                    select.selectByVisibleText((String) selectDropDownValue);
                    break;

                default:
                    throw new IllegalArgumentException("Invalid select method: " + selectMethod);
            }

        } catch (Exception e){
            CustomException.handleException("SelectElement" + fieldName, e);
        }
    }


    public boolean verifyElementIsVisible(WebElement element, String fieldName) {

        try{
            Wait<WebDriver> wait = initFluentWait();
            wait.until(ExpectedConditions.visibilityOf(element));

            if (element.isDisplayed()){
                System.out.println("Element: " + fieldName + " is visible");
                return true;
            }

        } catch (Exception e){
            CustomException.handleException("verifyElementIsVisible" + fieldName, e);
        }
        System.out.println("Element: " + fieldName + " is not visible");
        return false;
    }

    public boolean verifyElementIsNotVisible(WebElement element, String fieldName) {

        try{
            Wait<WebDriver> wait = initFluentWait();
            wait.until(ExpectedConditions.invisibilityOf(element));

            boolean elementNotVisible = element.isDisplayed();

            if (!elementNotVisible){
                System.out.println("Element: " + fieldName + " is not visible");
                return true;
            }

        } catch (Exception e){
            CustomException.handleException("verifyElementIsNotVisible" + fieldName, e);
        }
        System.out.println("Element: " + fieldName + " is visible");
        return false;
    }

    public void acceptAlert() {
        try {
            Wait<WebDriver> wait = initFluentWait();
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = BrowserDriverFactory.getInstance().getDriver().switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            CustomException.handleException("AcceptAlert", e);
        }
    }

    public void dismissAlert() {
        try {
            Wait<WebDriver> wait = initFluentWait();
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = BrowserDriverFactory.getInstance().getDriver().switchTo().alert();
            alert.dismiss();
        } catch (Exception e) {
            CustomException.handleException("DismissAlert", e);
        }
    }

    public void sendTextToAlert(String text) {
        try {
            Wait<WebDriver> wait = initFluentWait();
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = BrowserDriverFactory.getInstance().getDriver().switchTo().alert();
            alert.sendKeys(text);
        } catch (Exception e) {
            CustomException.handleException("SendTextToAlert", e);
        }
    }

    public String getTextFromElement(WebElement element, String fieldName) {
        try {
            Wait<WebDriver> wait = initFluentWait();
            wait.until(ExpectedConditions.visibilityOf(element));

            // Direct text retrieval
            String text = element.getText();
            System.out.println("Text from element " + fieldName + " using getText(): " + text);

            // Use JavaScript Executor if text is empty
            if (text.isEmpty()) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) BrowserDriverFactory.getInstance().getDriver();

                // Try getting innerText first
                text = (String) jsExecutor.executeScript("return arguments[0].innerText;", element);
                System.out.println("Text from element " + fieldName + " using JavaScript innerText: " + text);

                // If innerText is still empty, try getting value attribute
                if (text.isEmpty()) {
                    text = (String) jsExecutor.executeScript("return arguments[0].value;", element);
                    System.out.println("Text from element " + fieldName + " using JavaScript value attribute: " + text);
                }
            }

            // Throw exception if text is still empty
            if (text.isEmpty()) {
                throw new RuntimeException("Text is empty for element " + fieldName);
            }

            return text;
        } catch (Exception e) {
            CustomException.handleException("GetTextFromElement " + fieldName, e);
            throw new RuntimeException("Error retrieving text from element " + fieldName, e);
        }
    }


    public void writeTextToFile(String text) {
        try {
            assert filePath != null;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(text);
                System.out.println("Text written to file: " + filePath);
            }
        } catch (IOException e) {
            CustomException.handleException("WriteTextToFile " + filePath, e);
        }
    }

    public String readTextFromFile() {

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            System.out.println("Text read from file: " + filePath);
        } catch (IOException e) {
            CustomException.handleException("ReadTextFromFile " + filePath, e);
        }
        return content.toString();
    }
}
