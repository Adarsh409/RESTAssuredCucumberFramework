package com.qa.booker.resources;

public enum APIEndpoints {
	CreateBooking("/booking"),
	GetBookingByID("/booking/{id}"),
	GetBookings("/booking"),
	DeleteBooking("/booking/{id}"),
	UpdateBooking("/booking/{id}"),
	Auth("/auth");

	private String resource;
	APIEndpoints(String resource) {
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}

}
