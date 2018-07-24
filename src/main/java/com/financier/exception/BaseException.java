package com.financier.exception;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 5161534446639116004L;

	public final String errorCode;
	public final String status;
	public final String message;

	public BaseException(String exception, String errorCode, String status, String message) {
		super(exception);
		this.errorCode = errorCode;
		this.status = status;
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
