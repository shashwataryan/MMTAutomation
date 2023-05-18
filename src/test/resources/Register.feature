Feature: Register to MMT

@regression
Scenario: Register to MMT to list your own hotel

Given User opens MMT
And Clicks on the link to list hotel
When User enter the valid details to register
Then A link to the registered email id is sent