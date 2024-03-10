Feature: Validating CreateBooking API

@CreateBooking
Scenario: Create Booking using data from JSON file
Given User has CreateBooking payload
When User calls CreateBooking API 
Then User gets response code 200
And User validates JSON schema "CreateBookingSchema.json"
