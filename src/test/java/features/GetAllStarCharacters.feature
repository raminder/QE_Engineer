Feature: Get All Star War Characters 

Scenario: User is able to get all star war character details 
	Given user calls "people" with "valid" get request 
	Then api response got success with status code 200 
	And user should see "all" star war character details 
	
Scenario: User is able to get all star war character details 
	Given user calls "peoples" with "invalid" get request 
	Then api response got success with status code 404