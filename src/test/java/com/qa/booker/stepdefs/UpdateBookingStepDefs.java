package com.qa.booker.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.cucumber.java.en.Given;
import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import com.qa.booker.pojo.Booking;
import com.qa.booker.pojo.BookingDetails;
import com.qa.booker.resources.APIEndpoints;
import com.qa.booker.resources.RequestResponseHandler;
import com.qa.booker.resources.TestContext;
import com.qa.booker.utils.RestAssuredRequestFilter;

public class UpdateBookingStepDefs {
	private TestContext testContext;
	private BookingDetails bookingDetailsObj;
	private Booking bookingObj;

	public UpdateBookingStepDefs(TestContext testContext) {
		this.testContext = testContext;
	}

	@Given("User has the booking id to update")
	public void user_has_the_booking_id_to_update() {
		bookingDetailsObj = RequestResponseHandler.getBookingDetailsObj();
		testContext.requestSpecification = given().spec(testContext.requestSetUp()).pathParam("id",
				bookingDetailsObj.getBookingid());
	}

	@When("User calls UpdateBooking API with details")
	public void user_calls_update_booking_api_with_details(DataTable updateDetailsTable) {
		List<Map<String, String>> updateDetailsMap = updateDetailsTable.asMaps(String.class, String.class);
		APIEndpoints obj = APIEndpoints.valueOf("UpdateBooking");
		bookingObj = RequestResponseHandler.serializeRequest(updateDetailsMap.get(0));
		testContext.response = testContext.requestSpecification.body(bookingObj)
				.header("Cookie", testContext.accessToken)
				.filter(new RestAssuredRequestFilter()).when()
				.put(obj.getResource()).then().extract().response();
	}

}
