
package com.financier.exception;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class ValidationError {
    private String field;
    private String code;
    private String reason;

    public ValidationError(String field, String code, String reason) {
        this.field = field;
        this.code = code;
        this.reason = reason;
    }

    public String getField() {
        return field;
    }

    public String getReason() {
        return reason;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "field='" + field + '\'' +
                ", code='" + code + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
