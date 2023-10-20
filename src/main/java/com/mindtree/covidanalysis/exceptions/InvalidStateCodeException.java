package com.mindtree.covidanalysis.exceptions;

public class InvalidStateCodeException extends RuntimeException {
	
	
	public InvalidStateCodeException() {
		
	}
	public InvalidStateCodeException(String message) {
		super(message);
	}

}
