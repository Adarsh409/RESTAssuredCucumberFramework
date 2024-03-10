package com.qa.booker.stepdefs;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.junit.Assert;

import com.qa.booker.pojo.Booking;
import com.qa.booker.pojo.BookingDetails;
import com.qa.booker.pojo.BookingID;
import com.qa.booker.resources.APIEndpoints;
import com.qa.booker.resources.RequestResponseHandler;
import com.qa.booker.resources.TestContext;
import com.qa.booker.utils.RestAssuredRequestFilter;

import io.cucumber.java.en.Given;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;

public class GetBookingStepDefs {
	private TestContext testContext;
	private BookingDetails bookingDetailsObj;
	private Booking bookingObj;

	public GetBookingStepDefs(TestContext testContext) {
		this.testContext = testContext;
	}

	@Given("User has GetBooking request with first name and last name")
	public void user_has_get_booking_request_with_first_name_and_last_name() {
		bookingDetailsObj = /* testContext.getBookingDetailsObj(); */ RequestResponseHandler.getBookingDetailsObj();
		HashMap<String, Object> queryParamsHashMap = new HashMap<String, Object>();
		queryParamsHashMap.put("firstname", bookingDetailsObj.getBooking().getFirstname());
		queryParamsHashMap.put("lastname", bookingDetailsObj.getBooking().getLastname());
		testContext.requestSpecification = given().spec(testContext.requestSetUp()).queryParams(queryParamsHashMap)
				.filter(new RestAssuredRequestFilter());

	}

	@When("User calls GetBookings API")
	public void user_calls_get_bookings_api() {
		APIEndpoints obj = APIEndpoints.valueOf("GetBookings");
		testContext.response = testContext.requestSpecification.expect().defaultParser(Parser.JSON).when()
				.get(obj.getResource());
	}

	@Then("List of booking ids is returned")
	public void list_of_booking_ids_is_returned() {
		BookingID[] bookingIDs = testContext.response.as(BookingID[].class);
		Assert.assertNotNull(bookingIDs);
	}

	@Then("User calls GetBookingByID API with booking id")
	public void user_calls_get_booking_by_id_api_with_booking_id() {
		int bookingId = testContext.response.getBody().jsonPath().getInt("[0].bookingid");
		APIEndpoints obj = APIEndpoints.valueOf("GetBookingByID");
		testContext.response = given().spec(testContext.requestSetUp()).pathParam("id", bookingId).when()
				.get(obj.getResource()).then().extract().response();

	}

	@Then("User validates the first name and last name from the record returned")
	public void user_validates_the_first_name_and_last_name_from_the_record_returned() {
		bookingObj = testContext.response.as(Booking.class);
		Assert.assertEquals(bookingDetailsObj.getBooking().getFirstname(), bookingObj.getFirstname());
		Assert.assertEquals(bookingDetailsObj.getBooking().getLastname(), bookingObj.getLastname());
	}
}
