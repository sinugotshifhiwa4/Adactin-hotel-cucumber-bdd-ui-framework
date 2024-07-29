package com.adactinhotel.stepDefinitions;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com/adactinhotel/stepDefinitions"},
        plugin = {"pretty", "json:target/cucumber-reports/Cucumber.json"},
        //tags = "@validCredentials or @invalidCredentials or @bookHotel or @cancelBooking",
        tags = "@bookHotel",
        monochrome = true
)
public class TestRunner {
}
