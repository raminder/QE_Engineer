Feature: Search Planets 

Scenario: User is able to get specific planet details, matching search term 
	Given user calls "valid" "planet" api with "Alderaan" as search term 
	Then api response got success with a status code 200 
	And user should see "one" result containing "Alderaan" in name 
	
Scenario: User is able to get multiple planet details, matching search term 
	Given user calls "valid" "planet" api with "oth" as search term 
	Then api response got success with a status code 200 
	And user should see "many" result containing "oth" in name 
	
Scenario: User is able to get no planet details, matching search term 
	Given user calls "valid" "planet" api with "ooo" as search term 
	Then api response got success with a status code 200 
	And user should see "no" result containing "ooo" in name 
	
Scenario: User is able to get no planet details for invalid api url 
	Given user calls "invalid" "planet" api with "in" as search term 
	Then api response got success with a status code 404