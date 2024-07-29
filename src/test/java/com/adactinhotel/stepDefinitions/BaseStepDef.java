package com.adactinhotel.stepDefinitions;

import com.adactinhotel.pages.LoginPage;
import com.adactinhotel.pages.SearchHotelPage;
import com.adactinhotel.pages.SelectHotel;
import com.adactinhotel.reusableComponents.CustomException;
import com.adactinhotel.reusableComponents.PropertiesConfig;
import com.adactinhotel.utils.BrowserDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;

public class BaseStepDef {

    private BrowserDriverFactory browserDriverFactory;
    private WebDriverWait wait;
    protected LoginPage loginPage;
    protected SearchHotelPage searchHotelPage;
    protected SelectHotel selectHotel;

    @Before
    public void setup() {

        try {
            browserDriverFactory = BrowserDriverFactory.getInstance();
            browserDriverFactory.setDriver(browserDriverFactory.initBrowserDriver(Objects.requireNonNull(PropertiesConfig.getPropertyKey("browser"))));
            WebDriver driver = browserDriverFactory.getDriver();
            driver.get(PropertiesConfig.getPropertyKey("baseUrl"));
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            loginPage = new LoginPage(driver);
            searchHotelPage = new SearchHotelPage(driver);
            selectHotel = new SelectHotel(driver);


        } catch (Exception e) {
            CustomException.handleException("setupMethod", e);
        }
    }

    @AfterStep
    public void captureScreenshot(Scenario scenario){
        try {
            Thread.sleep(1000);
            final byte[] takeScreenshot = ((TakesScreenshot) browserDriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
            Date date = new Date();
            String formatDate = simpleDateFormat.format(date);
            scenario.attach(takeScreenshot, "image/png", "image-" + formatDate + ".png");
        } catch (Exception e){
            CustomException.handleException("captureScreenshot", e);
        }
    }


    @After
    public void tearDown() {
        browserDriverFactory.quitDriver();
    }
}
