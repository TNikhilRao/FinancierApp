package com.financier.util;


/**
 * @author Nikhil.Tirmanwar
 *
 */
public enum ResultCode {

    SUCCESS("0000", "success", ""),
    SUCCESS_GET_FINANCIERS("0000", "success", "Financier Details fetched successfully"),
    SUCCESS_ADD_FINANCIIER("0000", "success", "Financier registered successfully"),
    FAILURE_ADD_LOYALTY_ACCOUNT("E0013", "failure", "Failed to add customer loyalty ID"),
    FAILURE_GET_LOYALTY_CARDS("E0014", "failure", "Failed to retrieve List of Loyalty Cards"),
    FAILURE("E0011", "failure", ""),
    INVALID_CUSTOMER_PROFILE("400.100", "failure", "Invalid Customer Profile"),
    FAILURE_LOYALTY_CARD_EXISTS("E0016", "failure", "Customer Loyalty Card already exists"),
    FAILURE_CONNECT_PROFILE_PROVIDER("500.100", "failure", "Failure connecting to profile-provider-api"),
    FAILURE_MERCHANT_CORE("E0019", "failure", "failure at merchant-core-api "),
    FAILURE_PROFILE_PROVIDER_NULL_RESPONSE("500.200", "failure", "Null response from profile-provider-api"),
    FAILURE_CONNECT_MERCHANT_CORE("500.300", "failure", "Failure connecting to merchant-core-api "),
    FAILURE_CARDS_NOT_FOUND("400.200", "failure", "Financier Not Found"),
    FAILURE_CUSTOMER_NOT_FOUND("E0023", "failure", "Customer Not found"),
    SUCCESS_GET_TRANSACTION_HISTORY("0000", "success", "Customer Transaction History fetched successfully");

    private String code;
    private String status;
    private String message;

    /**
     * @param code
     * @param status
     * @param message
     */
    private ResultCode(String code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    public static ResultCode getResponseStatusByCode(final String value) {
        ResultCode responseStatusToReturn = null;
        for (ResultCode resultCode : ResultCode.values()) {
            if (value != null && resultCode.getCode().equals(value)) {
                responseStatusToReturn = resultCode;
                break;
            }
        }
        return responseStatusToReturn;
    }
}
