Feature: Get All Star War Characters

Scenario: User is able to get all star war character details
    Given user calls "people" with get request
    Then api response got success with status code 200
    And user should see 10 star war character in response