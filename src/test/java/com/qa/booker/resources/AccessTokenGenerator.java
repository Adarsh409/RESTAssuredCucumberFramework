package com.qa.booker.resources;

import static io.restassured.RestAssured.given;

import com.qa.booker.pojo.Auth;

public class AccessTokenGenerator {

	public static String getAccessToken(String userName, String password) {
		APIEndpoints obj = APIEndpoints.valueOf("Auth");
		TestContext testContextObj = new TestContext();
		Auth authObj = null;
		try {

			authObj = given().spec(testContextObj.requestSetUp()).when().body(formAccessTokenRequest(userName, password))
					.post(obj.getResource()).then().extract().response().as(Auth.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "token=" + authObj.getToken();
	}

	public static String formAccessTokenRequest(String userName, String password) {
		return "{\r\n" + "    \"username\" : \"" + userName + "\",\r\n" + "    \"password\" : \"" + password + "\"\r\n"
				+ "}";
	}

}
