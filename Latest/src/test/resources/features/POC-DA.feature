@DA-POC
Feature: MLB Ind DA Lvl10

	@TC-TBD
	Scenario Outline: New Money Deal setup for DA Ind Lvl 10 - Negative testing (Loan Amount > $150,000)

#		Application activity

		Given Generating new test data in test data file "<testDataFilePath>"
		When Reading test data from excel file "<testDataFilePath>"
		When User launches CME as "MABDUHOLIKOV0418" and verifies Dashboard screen loaded
		And User starts "New Deal Creation" wizard
		Then User enters mandatory fields in Application screen
		Then User adds <1> Entities and clicks "No" to Add-View Related Entities pop up
		Then User adds <1> Facilities and continues to add collateral
		And User adds <1> new Collaterals and continues to Collateral Analysis screen
		And User continues to Stockholder screen and adds voter - Entity <1>
		Then User continues to Fees screen and adds Fees
		And User continues to DDT screen and assigns Status to "Requested"
		Then User continues to "Related Documents" screen
		And User continues to Route screen and routes the deal to "Traditional Due Diligence" to "Muzaffar Abduholikov"

#		Due Diligence Activity

		Then User loads the deal by Application Number and navigates to Facility screen
		And User Gets Rate, Calculates payment, adds Risk types and verifies YBS calculations for Facilities
		And User continues to Deal Models screen and performs Optimist steps
		Then User continues to Guideline Compliance screen and verifies questionary
		Then User continues to Covenants screen to add covenants
		Then User continues to print Draft CDA
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Credit Decision" to "Muzaffar Abduholikov"
		Then User validates Approval Level for "SVCCMETESTUSER02"

		Examples:
			|testDataFilePath							 |
			|src\\test\\resources\\testData\\POC_DA.xlsm|

	@TC-TBD
	Scenario Outline: Credit Decision Activity
		Given Reading test data from excel file "<testDataFilePath>"
		When User launches CME as "SVCCMETESTUSER02" and verifies Dashboard screen loaded
		And User loads the deal in Credit Decision and continues to Authorization screen
		Then User proceeds to approve the deal with a given Approval level

		Examples:
			|testDataFilePath							 |
			|src\\test\\resources\\testData\\POC_DA.xlsm|


