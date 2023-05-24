Feature: Validate Filter and Final Amount 

Background: 
Given User opens MMT
And Search for hotel "Goa"

@SmokeTest
Scenario: Validate the final amount paid for Hotel

When User select the hotel from search results
Then The same amount should show in the booking review page and payments page

@regression
Scenario: Validate the filter applied on Hotel

When User applies a filter according to the star category
Then The results should be filtered accordingly