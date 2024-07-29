package com.adactinhotel.utils;

import com.adactinhotel.reusableComponents.PropertiesConfig;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GenerateHtmlReport {

    public void generateReport() {

        File reportOutputDirectory = new File("target/cucumber-reports");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-reports/Cucumber.json");

        String buildNumber = PropertiesConfig.getPropertyKey("buildNumber");
        String projectName = PropertiesConfig.getPropertyKey("projectName");

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setBuildNumber(buildNumber);
        configuration.addClassifications("Environment", PropertiesConfig.getPropertyKey("baseUrl"));
        configuration.addClassifications("Browser", PropertiesConfig.getPropertyKey("browser"));
        configuration.addClassifications("Branch", "release/1.0");
        configuration.addClassifications("Platform", System.getProperty("os.name"));
        configuration.addClassifications("Version", System.getProperty("os.version"));
        configuration.addClassifications("User", System.getProperty("user.name"));

        // Add graphs
        configuration.setTrendsStatsFile(new File("target/test-classes/cucumber-trends.json"));


        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

    public static void main(String[] args) {
        new GenerateHtmlReport().generateReport();
    }
}
