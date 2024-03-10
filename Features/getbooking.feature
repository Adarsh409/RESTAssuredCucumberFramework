Feature: Retrieve booking details feature

@GetBookingByName
Scenario: To view the bookings by passing first name and last name
Given User has GetBooking request with first name and last name
When User calls GetBookings API
Then User gets response code 200
And List of booking ids is returned
And User calls GetBookingByID API with booking id
And User gets response code 200
And User validates JSON schema "GetBookingByIdSchema.json"
And User validates the first name and last name from the record returned