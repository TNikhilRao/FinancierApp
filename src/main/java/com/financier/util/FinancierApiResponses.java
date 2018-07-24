package com.financier.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.financier.exception.ErrorResponse;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
@ApiResponse(code = 400, message = ErrorTypeDescription.VALIDATION_ERROR, response = ErrorResponse.class),
@ApiResponse(code = 401, message = ErrorTypeDescription.SECURITY_ERROR, response = ErrorResponse.class),
@ApiResponse(code = 422, message = ErrorTypeDescription.BUSINESS_RULE_VIOLATION_ERROR, response = ErrorResponse.class),
@ApiResponse(code = 500, message = ErrorTypeDescription.INTERNAL_SERVER_ERROR, response = ErrorResponse.class) })
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface FinancierApiResponses {

}
