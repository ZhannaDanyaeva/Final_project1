Feature: Advertisement Management
  As an authorized user
  I want to be able to manage my advertisements
  So that I can keep information about sold items up to date

  Scenario: Create new advertisement
    Given the user is logged in to the system
    When the user creates a new advertisement
    Then the advertisement is successfully created

  Scenario: Edit existing advertisement
    Given the user is logged in to the system
    And the user creates a new advertisement
    When the user edits their advertisement
    Then the advertisement is successfully edited

  Scenario: Delete advertisement
    Given the user is logged in to the system
    And the user creates a new advertisement
    When the user deletes their advertisement
    Then the advertisement is successfully deleted