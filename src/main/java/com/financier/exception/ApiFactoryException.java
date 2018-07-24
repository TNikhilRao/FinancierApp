package com.financier.exception;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class ApiFactoryException extends RuntimeException {
    
	private static final long serialVersionUID = -8831035742490922401L;
	private String code;

    ApiFactoryException(String message) {
        super(message);
    }

    ApiFactoryException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
