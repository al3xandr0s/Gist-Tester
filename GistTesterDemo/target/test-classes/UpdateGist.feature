@UpdateGist @group1
Feature: Update a Gist
  
  As a user
  I want to update my Gist
  To keep it up-to-date with my latest changes

  Background:
    Given I have a valid Github access token


  Scenario: Update Gist description

    Given I have a Gist I can edit
    When I update the description of the Gist to <"NEW DESCRIPTION">
    Then the response code should be 200
    And the Gist should show the updated description


  Scenario: Update Gist with new content

    Given I have a Gist I can edit
    When I update the content of the Gist to <"NEW CONTENT">
    Then the response code should be 200
    And the Gist should show the updated content


  Scenario: Update Gist filename

    Given I have a Gist I can edit
    When I update the filename of the Gist to <"NEW_FILE.md">
    Then the response code should be 200
    And the Gist should show the updated filename


  Scenario: Update Gist with empty content

    Given I have a Gist I can edit
    When I update the content of the Gist to empty
    Then I should receive response code 422
    And the error message should include <"Invalid request">