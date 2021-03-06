Feature: devTo basic features
  Scenario: Open First seeing blog
    Given Chrome browser is open
    When I go to devto main page
    And I click on first blog displayed
    Then I should be redirected to blog page