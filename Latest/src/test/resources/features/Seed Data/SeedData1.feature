@US57661
Feature: Seed Data - CFC Traditional Deal
	@US-Deal1
	Scenario Outline:
#	Application
		When Reading test data from excel file "<testDataFilePath>"
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		And User starts "New Deal Creation" wizard
		Then User enters mandatory fields in Application screen
		Then User adds <1> Existing Entities and clicks "No" to Add-View Related Entities pop up
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
		Then User updates "PD Rating"
		Then User continues to Guideline Compliance screen and verifies questionary
		Then User continues to Covenants screen to add covenants
		Then User continues to print Draft CDA
		And User continues to DDT screen and assigns Status to "Completed"
		And User continues to Route screen and routes the deal to "Credit Decision" to "Muzaffar Abduholikov"

		Examples:
			|testDataFilePath							 |
			|src/test/resources/testData/SeedData/AgTX-Deal2.xlsm|



