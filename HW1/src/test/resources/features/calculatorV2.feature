@tag
Feature: CalculatorV2

  Scenario: multiply two Integer numbers
    Given Two input values, 6 and 2 and operation *
    When I Press = button
    Then I expect the result 12 on calculator

  Scenario: divide two Integer numbers
    Given Two input values, 6 and 2 and operation /
    When I Press = button
    Then I expect the result 3 on calculator

  Scenario: Integer number to the power another Integer number
    Given Two input values, 6 and 2 and operation ^
    When I Press = button
    Then I expect the result 36 on calculator

  Scenario Outline: use different operations
    Given Two input values, <first> and <second> and operation <operation>
    When I Press = button
    Then I expect the result <result> on calculator

  Examples:
    | first | second | operation | result |
    | 1     | 12     | *         | 12     |
    | -1    | 6      | *         | -6     |
    | 2     | 0      | *         | 0      |
    | 0     | 32     | *         | 0      |
    | 9     | -9     | *         | -81    |
    | -3    | -2     | *         | 6      |
    | 12    | 3      | /         | 4      |
    | 9     | 2      | /         | 4      |
    | 3232  | 100    | /         | 32     |
    | 74    | 5      | /         | 14     |
    | -16   | 4      | /         | -4     |
    | -36   | -3     | /         | 12     |
    | 81    | -27    | /         | -3     |
    | 87    | 0      | /         | 0      |
    | 0     | 32     | /         | 0      |
    | 3     | 2      | ^         | 9      |
    | 4     | 3      | ^         | 64     |
    | -9    | 2      | ^         | 81     |
    | -2    | 3      | ^         | -8     |
    | -3    | 0      | ^         | 1      |
    | 4     | -1     | ^         | 0      |

