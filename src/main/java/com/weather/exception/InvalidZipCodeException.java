package com.weather.exception;

import java.text.MessageFormat;

//Class to create and exception for an invalid zip code provided
public class InvalidZipCodeException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidZipCodeException(String zip){
		 super(MessageFormat.format("{0} is an invalid zip code", zip));
	 }
}
