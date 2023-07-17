package com.jo.managment.api.exception;

public class CourseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorAndSuccessMessagges error;

	public CourseException(ErrorAndSuccessMessagges error) {
		this.error = (error);
	}

	public ErrorAndSuccessMessagges getError() {
		return error;
	}

}
