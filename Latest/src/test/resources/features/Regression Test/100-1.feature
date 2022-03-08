@Scenario-100-1
Feature: Agfast Deal Regression Test

	@TC-57979
	Scenario Outline: Application Activity
		Given Generating new test data "<testDataFilePath>" for Association "011" in Environment "FBLINTTST"
		When Reading test data from excel file "<testDataFilePath>"
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		And User starts "Agfast Application" wizard
		Then User enters mandatory fields in Application screen
		Then User adds <2> Entities and clicks "Yes" to Add-View Related Entities pop up
		And User enters Scored Financials and continues to Product Info screen
		Then User adds <1> Facilities and continues to add collateral
		And User adds <1> new Collaterals and continues to Collateral Analysis screen
		And User continues to Stockholder screen and adds voter - Entity <1>
		Then User continues to Fees screen and adds Fees
		And User continues to DDT screen and assigns Status to "Requested"
		Then User continues to "Related Documents" screen
		And User continues to Route screen and routes the deal to "AgFast Due Diligence" to "Muzaffar Abduholikov"

		Examples:
		|testDataFilePath|
		|src\\test\\resources\\testData\\Regression\\100-1.xlsm|

	@TC-57980
	Scenario: Due Diligence Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		Then User loads the deal by Application Number and navigates to Facility screen
		And User navigates liquid credit screen and verify liquid credit scores
		And User Gets Rate, Calculates payment, adds Risk types and verifies YBS calculations for Facilities
		And User continues to Deal Models screen and performs Optimist steps
		Then User continues to Guideline Compliance screen and verifies questionary
		Then User continues to Covenants screen to add covenants
		Then User continues to print Draft CDA
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Credit Decision" to "Muzaffar Abduholikov"

	@TC-57738
	Scenario: Credit Decision Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		And User loads the deal in Credit Decision and continues to Authorization screen
		Then User approves the deal and completes CDA
		And User continues to Route screen and routes the deal to "Credit Approved" to "Muzaffar Abduholikov"

	@TC-57739
	Scenario: Credit Approved Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		Then User loads the deal in Credit Approved and Verifies Pre-Closing CSi Documents
		And User continues to add Title Company related entity
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Pre-Closing" to "Muzaffar Abduholikov"

	@TC-57740
	Scenario: Pre-Closing Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		And User loads the deal in Pre-Closing and adds new disbursement
		And User continues to Documents screen and verifies Closing CSi Documents
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Closing - Doc Execution" to "Muzaffar Abduholikov"

	@TC-57741
	Scenario: Closing Doc Execution Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		And User loads the deal in Closing Doc Execution and Upload, Archive ECM Documents
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Closing - Fund/Book" to "Muzaffar Abduholikov"

	@TC-57866
	Scenario: Closing Fund/Book Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		Then User loads the deal in Closing and books the deal via "FBL"
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Post-Closing" to "Muzaffar Abduholikov"

	@TC-57743
	Scenario: Post Closing Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		Then User loads the deal in Post-Closing and navigates to DDT screen
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Post Closing - RE Certification" to "Muzaffar Abduholikov"

	@TC-57852
	Scenario: Pending - Post Closing - RE Certification
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		Then User loads the deal in Post-Closing RE Certification and navigates to DDT screen
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Complete" to "Muzaffar Abduholikov"

