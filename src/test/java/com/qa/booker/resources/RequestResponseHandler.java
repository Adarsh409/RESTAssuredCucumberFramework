package com.qa.booker.resources;


import java.util.Map;

import com.qa.booker.pojo.Booking;
import com.qa.booker.pojo.BookingDates;
import com.qa.booker.pojo.BookingDetails;


public class RequestResponseHandler {
	public static BookingDetails bookingDetailsObj;
	

	public static BookingDetails getBookingDetailsObj() {
		return bookingDetailsObj;
	}


	public static void setBookingDetailsObj(BookingDetails bookingDetailsObj) {
		RequestResponseHandler.bookingDetailsObj = bookingDetailsObj;
	}


	public static Booking serializeRequest(Map<String, String> requestDetailsMap) {
		Booking bookingObj = new Booking();
		BookingDates bookingDatesObj = new BookingDates();
		bookingObj.setFirstname(requestDetailsMap.get("firstname"));
		bookingObj.setLastname(requestDetailsMap.get("lastname"));
		bookingObj.setTotalprice(Integer.valueOf(requestDetailsMap.get("totalprice")));
		bookingObj.setDepositpaid(Boolean.valueOf(requestDetailsMap.get("depositpaid")));
		bookingObj.setAdditionalneeds(requestDetailsMap.get("additionalneeds"));
		bookingDatesObj.setCheckin(requestDetailsMap.get("checkin"));
		bookingDatesObj.setCheckout(requestDetailsMap.get("checkout"));
		bookingObj.setBookingdates(bookingDatesObj);

		return bookingObj;
	}



}
