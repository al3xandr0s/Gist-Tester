@ListGist @group1
Feature: List Gists

  Scenario: List Public Gists
    Given I do not have a valid GitHub access token
    When I send a GET request to the 'public' Gist API endpoint
    Then the response status must be 200
    And The response body will be a JSON object containing an array of public gists


  Scenario: List all of the gists for the authenticated user
    Given I have a valid Github access token
    When I send a GET request to the Gist API for the authenticated user's gists
    Then the response status must be 200
    And the response body will be a JSON object containing an array of the authenticated user's gists


  Scenario: List all the starred Gists belonging to a user
    Given I have a valid Github access token
    When I make a GET request to the Starred Gists API endpoint
    Then the response status must be 200
    And the response will list the authenticated user's starred Gists
    And the response should include the creation date and update date