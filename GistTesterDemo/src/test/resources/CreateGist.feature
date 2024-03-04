@CreateGist @group1
Feature: Create a Gist

  Background:
    Given I have a valid Github access token


  Scenario: Create a new public Gist with a single file
    When I create a new public Gist with the following details:
      | Filename | Content    | Description |
      | file1.txt| file1 data | PUBLIC Gist File |
    Then the response code should be 201
    And the Gist details should match the input details
    And the Gist should be retrievable by its id


  Scenario: Create a new private Gist with multiple files
    When I create a new private Gist with the following details:
      | Filename    | Content     |
      | file1.txt   | file1 data  |
      | file2.txt   | file2 data  |
    Then the response code should be 201
    And the private Gist details should match the input details
    And the Gist should be retrievable by its id


  Scenario: Create a new public Gist with an empty file
    When I create a new Gist with an empty file
    Then the response code should be 422
    And the response message should include <"Invalid request">