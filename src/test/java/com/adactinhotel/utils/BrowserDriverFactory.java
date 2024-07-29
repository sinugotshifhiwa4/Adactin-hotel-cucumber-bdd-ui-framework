package com.adactinhotel.utils;

import com.adactinhotel.reusableComponents.CustomException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class BrowserDriverFactory {

    private static final BrowserDriverFactory instance = new BrowserDriverFactory();
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private BrowserDriverFactory() {}

    public static BrowserDriverFactory getInstance() {
        return instance;
    }

    public void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public WebDriver initBrowserDriver(String browserName) {

        switch (browserName.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                setDriver(new ChromeDriver(chromeOptions));
                break;

            case "firefox":
                 FirefoxOptions firefoxOptions = new FirefoxOptions();
                 firefoxOptions.addArguments("--headless");
                 setDriver(new FirefoxDriver(firefoxOptions));
                 break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                setDriver(new EdgeDriver(edgeOptions));
                break;

            default:
                throw new IllegalArgumentException("Invalid browser specified " + browserName);

        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return getDriver();
    }

    public void quitDriver(){
        try{
            WebDriver driver = threadLocalDriver.get();

            if(driver != null){
                Thread.sleep(4000);
                driver.quit();
            }
        } catch(InterruptedException e){
            CustomException.handleException("quitDriver", e);
        } finally {
            threadLocalDriver.remove();
        }
    }
}
