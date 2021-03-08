Feature: Get Specifc Star War Character Details 

Scenario: User is able to get specific star war character details 
	Given user calls people api with "valid" get request 
	Then api response got success with status code 200 
	And user should see "specific" star war character details 
	
Scenario: User is able to get no star war character details 
	Given user calls people api with "invalid" get request 
	Then api response got success with status code 404 
	And user should see "no" star war character details