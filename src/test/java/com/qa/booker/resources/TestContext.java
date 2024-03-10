package com.qa.booker.resources;



import java.io.PrintStream;

import com.qa.booker.utils.Constants;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {

	public RequestSpecification requestSpecification;
	public Response response;
	public String accessToken;
	public PrintStream log;
	public RequestLoggingFilter requestLoggingFilter;
	public ResponseLoggingFilter responseLoggingFilter;

	public RequestSpecification requestSetUp() {

		try {
			//log = new PrintStream(new FileOutputStream("logging.txt"),true);
			requestSpecification = new RequestSpecBuilder().setBaseUri(Constants.BASE_URI)
					.setContentType(Constants.CONTENT_TYPE).setAccept(Constants.ACCEPT).build();
			// .addFilter(RequestLoggingFilter.logRequestTo(log))
			// .addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
//			requestLoggingFilter = new RequestLoggingFilter(log);
//			responseLoggingFilter = new ResponseLoggingFilter(log);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return requestSpecification;
	}

}
