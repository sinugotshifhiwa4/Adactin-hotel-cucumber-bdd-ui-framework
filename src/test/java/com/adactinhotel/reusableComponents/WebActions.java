package com.adactinhotel.reusableComponents;

import com.adactinhotel.utils.BrowserDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WebActions {

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
}
