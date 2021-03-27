Feature: devTo basic features
  Scenario: Open first seeing blog
    Given I go to devto main page
    When I click on first blog displayed
    Then I should be redirected to blog site
  Scenario: Open and play first seeig podcast
    Given I go to devto main page
    When I click text podcast in main page
    When I click on first podcast displayed
    When I play the podcast
    Then Podcast should be played
  Scenario: Search the phrase
    Given I go to devto main page
    When I search for "python" phrase
    Then Top 3 blogs found should have correct phrase in title or snippet