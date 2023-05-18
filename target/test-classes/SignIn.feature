Feature: Login to MMT

@SmokeTest
Scenario Outline: Login to MMT with valid credentials

Given User opens MMT
When User Enters <email> and <password> and logs in
Then User is logged into his account

Examples:
|email|password|
|shashwat.aryan5@gmail.com|Aryan123|
|test123@yopmail.com|Ishu123|

