package com.qa.booker.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RestAssuredRequestFilter implements Filter {

	private static final Logger log = LogManager.getLogger(RestAssuredRequestFilter.class);
	static {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		Response response = ctx.next(requestSpec, responseSpec);
		log.info("==============================================================================");
		if (response.statusCode() != 200 && response.statusCode() != 201) {
			log.error(requestSpec.getMethod() + " " + requestSpec.getURI() + " => " + response.getStatusCode() + " "
					+ response.getStatusLine());
		} else {
			log.info(requestSpec.getMethod() + " " + requestSpec.getURI() + " \n Request Body =>"
					+ requestSpec.getBody() + "\n Response Status => " + response.getStatusCode() + " "
					+ response.getStatusLine() + " \n Response Body => " + response.getBody().prettyPrint());
		}
		
		return response;

	}

}
