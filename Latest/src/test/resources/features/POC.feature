@US-POC
Feature: Research and Development

	Scenario Outline: POC1

		When Reading test data from excel file "<testDataFilePath>"
		When User launches CME as "SVCCMETESTUSER01" and verifies Dashboard screen loaded
		And POC Steps app number ""


		Examples:
			|testDataFilePath|
			|src/test/resources/testData/SmokeTestData/US57648.xlsm|

