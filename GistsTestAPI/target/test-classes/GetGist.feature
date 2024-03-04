@GetGist @group1
Feature: Get a Gist

  Background: Given I have a valid Github access token

  Scenario: Get a specific Gist
    Given I have the ID of a specific Gist
    When I make a GET request to the Gist API with the Gist ID
    Then the response status code should be 200
    And the response should include the Gist owner's login name