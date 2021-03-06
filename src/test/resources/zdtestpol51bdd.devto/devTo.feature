Feature: DevTp basic features
  Scenario: Open first seeing blog
    Given I go to devto main page
    And I click on first blog displayed
    Then I should be redirected to blog site
  Scenario:
    Given I go to devto main page
    When I click text podcast in main page
    When I click on first cast displayed
    Then I should be redirected to cast site
