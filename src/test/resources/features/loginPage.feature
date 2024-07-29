# Description: This feature covers the login functionality of the application.

Feature: Login Feature

  @validCredentials
  Scenario: User is able to login with valid credentials
    Given the user is on the login page
    When the user enters valid username into the username field
    And the user enters valid password into the password field
    And the user clicks the login button
    Then the user should logged in successfully and navigated to the search hotel page

  @invalidCredentials
  Scenario: User is unable to login with invalid credentials
    Given the user is on the login page
    When the user enters an invalid username into the username field
    And the user enters an invalid password into the password field
    And the user clicks the login button
    Then the user should get an error message saying incorrect credentials
