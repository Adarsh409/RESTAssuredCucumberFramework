package com.qa.booker.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.junit.Assert;
import com.qa.booker.resources.APIEndpoints;
import com.qa.booker.resources.RequestResponseHandler;
import com.qa.booker.resources.TestContext;
import com.qa.booker.utils.Constants;
import com.qa.booker.utils.FileReader;
import com.qa.booker.utils.RestAssuredRequestFilter;

public class CreateBookingStepDefs {

	private TestContext testContext;

	public CreateBookingStepDefs(TestContext testContext) {
		this.testContext = testContext;

	}

	@Given("User has CreateBooking payload")
	public void user_has_create_booking_payload() {
		HashMap<String, String> hashMap = FileReader.readExcelData();
		testContext.requestSpecification = given().spec(testContext.requestSetUp())
				.body(RequestResponseHandler.serializeRequest(hashMap))
				.filter(new RestAssuredRequestFilter());
	}

	@When("User calls CreateBooking API")
	public void user_calls_create_booking_api() {
		APIEndpoints obj = APIEndpoints.valueOf("CreateBooking");
		testContext.response = testContext.requestSpecification.expect().defaultParser(Parser.JSON).when()
				.post(obj.getResource());
	}

	@Then("User gets response code {int}")
	public void user_gets_response_code(Integer statusCode) {
		testContext.response = testContext.response.then().extract().response();
		Assert.assertEquals(statusCode, (Integer) testContext.response.getStatusCode());
	}

	@Then("User validates JSON schema {string}")
	public void user_validates_json_schema(String schemaFileName) {
		testContext.response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(Constants.SCHEMA_FILE_PATH + schemaFileName));
	}

}
