package com.financier.util;

import com.google.common.base.MoreObjects;
import org.springframework.http.HttpStatus;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public enum ErrorType {
    AUTHENTICATION_ERROR(ErrorTypeDescription.SECURITY_ERROR, HttpStatus.UNAUTHORIZED),
    AUTHORIZATION_ERROR(ErrorTypeDescription.SECURITY_ERROR, HttpStatus.FORBIDDEN),
    INVALID_JSON_ERROR(ErrorTypeDescription.INVALID_JSON_ERROR, HttpStatus.BAD_REQUEST),
    VALIDATION_ERROR(ErrorTypeDescription.VALIDATION_ERROR, HttpStatus.BAD_REQUEST),
    BUSINESS_RULE_VIOLATION_ERROR(ErrorTypeDescription.BUSINESS_RULE_VIOLATION_ERROR, HttpStatus.UNPROCESSABLE_ENTITY),
    NOT_FOUND_ERROR(ErrorTypeDescription.NOT_FOUND_ERROR, HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(ErrorTypeDescription.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);

    private String category;
    private HttpStatus status;

    private ErrorType(String category, HttpStatus status) {
        this.category = category;
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                        .add("category", category)
                        .add("status", status)
                        .toString();
    }

}
