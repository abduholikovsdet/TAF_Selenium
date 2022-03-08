@Task-73432
Feature: Traditional Deal - Smoke Test
	@US-57648
	Scenario Outline: Traditional Deal

		Given Generating new test data "<testDataFilePath>" for Association "011" in Environment "FBLDATO"
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

#		Due Diligence Activity

		Then User loads the deal by Application Number and navigates to Facility screen
		And User Gets Rate, Calculates payment, adds Risk types and verifies YBS calculations for Facilities
		And User continues to Deal Models screen and performs Optimist steps
		Then User continues to Guideline Compliance screen and verifies questionary
		Then User continues to Covenants screen to add covenants
		Then User continues to print Draft CDA
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Credit Decision" to "Muzaffar Abduholikov"

#		Credit Decision Activity

		And User loads the deal in Credit Decision and continues to Authorization screen
		Then User approves the deal and completes CDA
		And User continues to Route screen and routes the deal to "Credit Approved" to "Muzaffar Abduholikov"

#		Credit Approved Activity

		Then User loads the deal in Credit Approved and Verifies Pre-Closing CSi Documents
		And User continues to add Title Company related entity
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Pre-Closing" to "Muzaffar Abduholikov"

#		Pre-Closing Activity

		And User loads the deal in Pre-Closing and adds new disbursement
		And User continues to Documents screen and verifies Closing CSi Documents
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Closing - Doc Execution" to "Muzaffar Abduholikov"

#		Closing Doc Execution Activity

		And User loads the deal in Closing Doc Execution and Upload, Archive ECM Documents
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Closing - Fund/Book" to "Muzaffar Abduholikov"

#		Closing Fund/Book Activity

		Then User loads the deal in Closing and books the deal via "FBL"
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Post-Closing" to "Muzaffar Abduholikov"

#		Post Closing Activity

#		Then User loads the deal in Post-Closing and navigates to DDT screen
#		And User continues to DDT screen and assigns Status to "Completed"
#		And User continues to Route screen and routes the deal to "Post Closing - RE Certification" to "Muzaffar Abduholikov"
#
##		Post Closing - RE Certification
#
#		Then User loads the deal in Post-Closing RE Certification and navigates to DDT screen
#		And User continues to DDT screen and assigns Status to "Completed"
#		And User continues to Route screen and routes the deal to "Complete" to "Muzaffar Abduholikov"

		Examples:
			|testDataFilePath							 |
			|src/test/resources/testData/SmokeTestData/US57648.xlsm|

