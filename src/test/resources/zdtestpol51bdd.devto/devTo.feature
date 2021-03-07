Feature: devTo basic features
  Scenario: Open first seeing blog
    Given I go to devto main page
    When I click on first blog displayed
    Then I should be redirected to blog site
  Scenario: Open first seeig podcast
    Given I go to devto main page
    When I click text podcast in main page
    When I click on first cast displayed
    Then I should be redirected to cast site
  Scenario: Search the testing phrase
    Given I go to devto main page
    When I search for testing phrase
    Then Top 3 blogs found should have testing in title