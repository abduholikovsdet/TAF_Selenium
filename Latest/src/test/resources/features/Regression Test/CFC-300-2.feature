@Scenario-300-2
Feature: CFC Traditional Deal Smoke Test: US-57661

	@TC-57662
	Scenario Outline: Application Activity

		Given Generating new test data "<testDataFilePath>" for Association "001" in Environment "FBLDATO"
		When Reading test data from excel file "<testDataFilePath>"
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
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
		Examples:
			|testDataFilePath											|
			|src\\test\\resources\\testData\\Regression\\CFC-300-2.xlsm	|

	@TC-57663
	Scenario: Due Diligence Activity

		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		Then User loads the deal by Application Number and navigates to Facility screen
		And In Facility screen user Gets Rate and Calculates payment
		Then User continues to Debt Information screen and says No to Revist DealModels Popup
		Then User continues to Guideline Compliance screen and verifies questionary
		Then User continues to Covenants screen to add covenants
        Then User continues to print Draft CDA CFC Trad
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Pre-Decision Review" to "Muzaffar Abduholikov"


	@TC-57664
	Scenario: Pre Decision Review Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		And User loads the deal in Pre Decision Review and continues to DDT screen
		And User continues to Route screen and routes the deal to "Credit Decision" to "Muzaffar Abduholikov"


	@TC-57665
	Scenario: Credit Decision Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		And User loads the deal in Credit Decision and continues to Authorization screen
		Then User approves the deal and completes CDA
		And User continues to Route screen and routes the deal to "Credit Approved" to "Muzaffar Abduholikov"


	@TC-57666
	Scenario: Credit Approved Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		Then User loads the deal in Credit Approved and Verifies Pre-Closing CSi Documents
		And User continues to add Title Company related entity
		And User continues to Route screen and routes the deal to "Pending - Pre-Closing" to "Muzaffar Abduholikov"


	@TC-57667
	Scenario: Pre-Closing Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		And CFC User loads the deal in Pre-Closing and navigates till Document screen
		And User continues to Documents screen and verifies Closing CSi Documents
		And User continues to Route screen and routes the deal to "Pre-Closing Doc Review" to "Muzaffar Abduholikov"


	@TC-57668
	Scenario: Pre Closing Doc Review Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		Then User loads the deal in Pre-Closing Doc Review and navigates to DDT screen
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Closing - Doc Execution" to "Muzaffar Abduholikov"

	@TC-57669
	Scenario: Closing Doc Execution Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		And User loads the deal in Closing Doc Execution and Upload, Archive ECM Documents
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Closing - Fund/Book" to "Muzaffar Abduholikov"

	@TC-57670
	Scenario: Closing Fund/Book Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		Then User loads the deal in Closing and books the deal via "OBS"
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Post-Closing" to "Muzaffar Abduholikov"


	@TC-58273
	Scenario: Post-Closing Activity
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		Then User loads the deal in Post-Closing and navigates to DDT screen
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Complete" to "Muzaffar Abduholikov"

