package com.financier.exception;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class FinancierNotFoundException extends BaseException {

	private static final long serialVersionUID = -5461410340490277973L;

	public FinancierNotFoundException(String errorCode, String status, String message) {
		super("FinancierNotFoundException", errorCode, status, message);
	}

}
