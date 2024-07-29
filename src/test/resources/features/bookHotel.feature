# Description: BookHotel successfully

Feature: Book hotel successfully

  Background: User should be logged in successfully
    Given the user is on the login page
    When the user enters valid username into the username field
    And the user enters valid password into the password field
    And the user clicks the login button
    Then the user should logged in successfully and navigated to the search hotel page

  @bookHotel
  Scenario: User books a hotel successfully
    Given the user is on the search hotel page
    When the user fills out all fields on the search hotel page and clicks the search button
    Then the user is navigated to the select hotel page
    And the user selects the hotel they want and clicks the continue button
    Then the user is navigated to the book hotel page
    And the user fills out all billing information and clicks the book now button
    Then the user has successfully booked the hotel and can see the order number

  @cancelBooking
  Scenario: Cancel an existing booking
    Given the user is on the search hotel page
    When the user clicks the booked itinerary menu
    And the user searches for the order number and clicks the go button
    Then the booked hotel is retrieved
    And the user selects the booked hotel and clicks the cancel button
    And the user accepts the popup to cancel the booking
    Then the booking is successfully canceled

