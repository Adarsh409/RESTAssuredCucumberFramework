package com.qa.booker.stepdefs;



import com.qa.booker.pojo.BookingDetails;
import com.qa.booker.resources.RequestResponseHandler;
import com.qa.booker.resources.TestContext;

import io.cucumber.java.Before;

public class Hooks {

	private TestContext testContext = new TestContext();
	BookingDetails bookingDetailsObj;

	CreateBookingStepDefs obj = new CreateBookingStepDefs(testContext);
	

	@Before("@GetBookingByName or @DeleteBooking or @UpdateBooking")
	public void beforeScenario() {
		obj.user_has_create_booking_payload();
		obj.user_calls_create_booking_api();
		bookingDetailsObj = testContext.response.as(BookingDetails.class);
		// testContext.setBookingDetailsObj(bookingDetailsObj);
		RequestResponseHandler.setBookingDetailsObj(bookingDetailsObj);

	}

}
