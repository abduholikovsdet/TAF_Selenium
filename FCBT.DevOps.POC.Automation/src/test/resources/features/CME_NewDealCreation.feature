Feature: New Deal Creation

Background: 
	Given User Launches CME 
	When User Verifies Dashboard screen loaded
	
Scenario: Adding New Entity Scenario

	Then User clicks New Deal Creation
	And User verifies Customer Search Page loaded
	And User Enters Customer name in Name Search field and Clicks New button
	Then User Verifies Entity Information screen loaded
	Then User Enters Customer Info and Saves

Scenario: Entity Maintenance
	
	Then User clicks Entity Maintenance from Tasks