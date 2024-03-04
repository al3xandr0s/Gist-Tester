@RateLimitQuotas @group2
Feature: Test  Rate Limiting Quotas

  Scenario: Get Rate Limit Status
    Given I have a valid Github access token
    When I send a GET request to the Github API rate limit endpoint
    Then the response status code will be 200
    And the response should include the rate limit status

  Scenario: Verify Rate Limiting Quotas
    Given  I do not have a valid GitHub access token
    When I send more than 160 unauthenticated requests to the Github API in 1 hour
    Then the response code should be 403
    And the response body should contain the error message <"API rate limit exceeded">

  Scenario: Verify Rate Limiting Quotas
    Given I have a valid Github access token
    When I send more than 1000 authenticated requests to the Github API in 1 hour
    Then the response code should be 403
    And the response body should contain the error message <"API rate limit exceeded">

