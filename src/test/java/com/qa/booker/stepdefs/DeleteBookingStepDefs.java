package com.qa.booker.stepdefs;

import com.qa.booker.pojo.BookingDetails;
import com.qa.booker.resources.APIEndpoints;
import com.qa.booker.resources.AccessTokenGenerator;
import com.qa.booker.resources.RequestResponseHandler;
import com.qa.booker.resources.TestContext;
import com.qa.booker.utils.RestAssuredRequestFilter;

import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class DeleteBookingStepDefs {

	private TestContext testContext;
	private BookingDetails bookingDetailsObj;

	public DeleteBookingStepDefs(TestContext testContext) {
		this.testContext = testContext;

	}

	@Given("User gets an access token by passing {string} as username and {string} as password")
	public void user_gets_an_access_token_by_passing_as_username_and_as_password(String userName, String pasword) {
		testContext.accessToken = AccessTokenGenerator.getAccessToken(userName, pasword);
	}

	@When("User calls DeleteBooking API with access token and booking id")
	public void user_calls_delete_booking_api_with_booking_id() {
		bookingDetailsObj = RequestResponseHandler.getBookingDetailsObj();
		APIEndpoints obj = APIEndpoints.valueOf("DeleteBooking");
		testContext.response = given().spec(testContext.requestSetUp()).header("Cookie", testContext.accessToken)
				.pathParam("id", bookingDetailsObj.getBookingid())
				.filter(new RestAssuredRequestFilter()).when()
				.delete(obj.getResource()).then().extract().response();
	}

}
