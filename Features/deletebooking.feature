Feature: Delete booking

@DeleteBooking
Scenario: To delete a booking
Given User gets an access token by passing "admin" as username and "password123" as password
When User calls DeleteBooking API with access token and booking id
Then User gets response code 201