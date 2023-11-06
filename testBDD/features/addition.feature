Feature: Adding integers
  Scenario Outline: add int
    Given I have a calculator
    When I add <num1> and <num2>
    Then I should have <result>
    Examples:
      |num1 |num2| result |
      | 12 | 5 | 17 |
      | 20 | 5 | 25 |
