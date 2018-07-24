package com.financier.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = FinancierNotFoundException.class)
	public ResponseEntity<ErrorResponse> LoyaltyCardNotFoundExceptionExceptionHandler(
			FinancierNotFoundException financierNotFoundException) {
		logger.error("FinancierNotFoundException Caught: ", financierNotFoundException);
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode(financierNotFoundException.getErrorCode());
		errorResponse.setStatus(financierNotFoundException.getStatus());
		errorResponse.setMessage(financierNotFoundException.getMessage());

		ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<ErrorResponse>(errorResponse, null,
				HttpStatus.BAD_REQUEST);

		return responseEntity;
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception exception) {
		logger.error("Exception Caught: ", exception);
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode("500");
		errorResponse.setStatus("failure");
		errorResponse.setMessage("Internal Server Error");

		ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<ErrorResponse>(errorResponse, null,
				HttpStatus.INTERNAL_SERVER_ERROR);

		return responseEntity;
	}

}
