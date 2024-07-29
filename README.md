# Adactin Hotel UI Automation

This project automates the login functionality of the Adactin Hotel application using Selenium, JUnit 5, and Cucumber. It includes log configurations with Log4j2 and generates HTML reports with ExtentReports.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Running Tests](#running-tests)
- [Project Structure](#project-structure)
- [Logging Configuration](#logging-configuration)
- [Test Reports](#test-reports)
- [Authors](#authors)

## Tags

- #Java
- #CucumberBDD
- #Gherkin
- #FeatureFiles
- #StepDefinitions
- #SingletonPattern
- #ThreadSafety
- #JUnit5
- #Log4j2
- #GenerateReport in html

## Prerequisites

- Java 8 or higher
- Maven 3.6.0 or higher

## Setup

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/adactin-hotel-ui-automation.git
    cd adactin-hotel-ui-automation
    ```

2. Install dependencies:
    ```sh
    mvn clean install
    ```

3. Create a `config.properties` file in the `src/test/resources` directory with the following content:
    ```properties
    username=your-username
    password=your-password
    invalidUsername=invalid-username
    invalidPassword=invalid-password
    projectName=your-project-name
    buildNumber=number
    selectByVisibleText=selectbyvisibletext
    selectByValue=selectbyvalue
    selectByIndex=selectbyindex
    ```

## Running Tests

To run the tests, use the following Maven command:
```sh
mvn clean test -P local

Project Structure

Adactin-hotel-cucumber-bdd-ui-framework
├── .idea
├── .mvn
├── logs
│   └── application.log
├── src
│   ├── test
│   │   ├── java
│   │   │   └── com
│   │   │       └── adactinhotel
│   │   │           ├── pages
│   │   │           │   ├── LoginPage.java
│   │   │           │   └── SearchHotelPage.java
│   │   │           ├── reusableComponents
│   │   │           │   ├── CustomException.java
│   │   │           │   ├── LoggerConfig.java
│   │   │           │   ├── PropertiesConfig.java
│   │   │           │   └── WebActions.java
│   │   │           ├── stepDefinitions
│   │   │           │   ├── BaseStepDef.java
│   │   │           │   ├── BookHotelStepDef.java
│   │   │           │   ├── CancelBookingStepDef.java
│   │   │           │   ├── LoginPageStepDef.java
│   │   │           │   └── TestRunner.java
│   │   │           └── utils
│   │   │               ├── BrowserDriverFactory.java
│   │   │               └── GenerateHtmlReport.java
│   │   └── resources
│   │       ├── features
│   │       │   ├── bookHotel.feature
│   │       │   └── loginPage.feature
│   │       ├── config.properties
│   │       └── log4j2.xml
├── target
├── .gitignore
├── mvnw
├── mvnw.cmd
└── pom.xml

Logging Configuration
Logging is configured using Log4j2. The configuration is set up in the log4j2.xml file located in the src/test/resources directory.

log4j2.xml
xml
Copy code
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <!-- Console Appender -->
        <Console name="ConsoleAppender" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %c{1} - %msg%n%throwable"/>
        </Console>

        <!-- Rolling File Appender -->
        <RollingFile name="RollingFileAppender" fileName="logs/application.log"
                     filePattern="logs/application-%d{yyyy-MM-dd}-%i.log.gz">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %c{1} - %msg%n%throwable"/>
            <Policies>
                <TimeBasedTriggeringPolicy />
                <SizeBasedTriggeringPolicy size="5MB"/>
            </Policies>
        </RollingFile>
    </Appenders>
    <Loggers>
        <!-- Root Logger -->
        <Root level="info">
            <AppenderRef ref="ConsoleAppender"/>
            <AppenderRef ref="RollingFileAppender"/>
        </Root>
    </Loggers>
</Configuration>


Test Reports
Test reports are generated using Cucumber and ExtentReports.

JSON and HTML Reports
JSON Report: The test results are first generated in JSON format.
HTML Report: The JSON report is then converted into an HTML report.

Report Location
The reports can be found in the target/cucumber-reports directory.
The JSON report is created during the test execution.
The HTML report is generated from the JSON report using the build setup.

Build Setup
To generate the HTML report from the JSON report, use the following Maven command:

mvn verify
This command will generate the HTML report in the target/cucumber-reports directory.


Authors
Tshifhiwa - Automation Tester - sinugotshifhiwa4
