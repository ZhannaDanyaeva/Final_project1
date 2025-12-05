Feature: User Registration
  As a new user
  I want to be able to register in the system
  So that I can post my advertisements

  Scenario: Successful registration with unique email
    Given the user is on the registration page
    When the user registers with a unique email
    Then the registration is successful

  Scenario: Attempt to register with existing email
    Given the user is on the registration page
    And the user registers with a unique email
    When the user tries to register with an existing email
    Then an error message about registration is displayed