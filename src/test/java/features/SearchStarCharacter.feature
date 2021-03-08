Feature: Search Star War Characters 

Scenario: 
	User is able to get specific star war character details, matching search term 
	Given user calls "people" with "Luke" as search term 
	Then api response got success with a status code 200 
	And user should see "one" result containing "Luke" in name 
	
Scenario: 
	User is able to get multiple star war character details, matching search term 
	Given user calls "people" with "an" as search term 
	Then api response got success with a status code 200 
	And user should see "many" result containing "an" in name 
	
Scenario: 
	User is able to get no star war character details, matching search term 
	Given user calls "people" with "null" as search term 
	Then api response got success with a status code 200 
	And user should see "no" result containing "null" in name 
	
Scenario: User is able to get no star war character details for invalid api url 
	Given user calls "peoples" with "in" as search term 
	Then api response got success with a status code 404