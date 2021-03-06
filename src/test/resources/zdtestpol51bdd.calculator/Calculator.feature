Feature: basic calculator functions
  Scenario: adding two numbers
    Given I have a calculator
    When I add 2 and 3
    Then I should get 5
  Scenario: dividing two numbers
    Given I have a calculator
    When I divide 6 and 2
    Then I should get 3
  Scenario: substracting two numbers
    Given I have a calculator
    When I substract 5 and 1
    Then I should get 4
  Scenario: mulitply two numbers
    Given I have a calculator
    When I multiply 9 and 3
    Then I should get 27