Feature: Update Booking

@UpdateBooking
Scenario: Update details of a booking
Given User gets an access token by passing "admin" as username and "password123" as password
And User has the booking id to update
When User calls UpdateBooking API with details
|firstname|lastname|totalprice|depositpaid|checkin|checkout|additionalneeds|
|John|Mary|200|false|2024-03-01|2024-03-21|Lunch|
Then User gets response code 200
And User validates JSON schema "UpdateBookingSchema.json"