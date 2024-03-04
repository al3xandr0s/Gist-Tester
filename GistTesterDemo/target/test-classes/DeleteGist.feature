@DeleteGist @group1
Feature: Delete a Gist

  Background:
    Given I have a valid Github access token
    Given I have a Gist to delete

  Scenario: Delete Gist

    When I delete the Gist
    Then the response code should be 204
    And the Gist should no longer exist
