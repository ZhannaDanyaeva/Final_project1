Feature: User Authorization
  As a registered user
  I want to be able to log in to the system
  So that I can access my account

  Scenario: Successful authorization
    Given a registered user exists
    And the user is on the login page
    When the user enters valid credentials
    Then the user is successfully logged in