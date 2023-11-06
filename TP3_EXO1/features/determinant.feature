Feature: Calculate determinant
  Scenario: determinant calculator
    Given I have a square matrix
      |1|2|3|
      |1|3|4|
      |1|4|7|
    When I calculate determinant
    Then I should have 2
