Feature: Get user by ID

  Background:
    Given I create new user Administrator

  Scenario: Get a specific user
    When I request with a id of users
    Then the response status code should be 200
    And the response should contain a user
