Feature: Tests

  Scenario: A first test
    Given I have 42 things
    And I visit "http://google.com"
    When I wait 1 second
    Then I should have 42 things


