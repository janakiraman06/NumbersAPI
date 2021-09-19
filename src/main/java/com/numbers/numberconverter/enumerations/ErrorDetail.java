package com.numbers.numberconverter.enumerations;

/**
 * @author Janakiraman Raghu
 *
 * This enum has all the possible error codes.
 */
public enum ErrorDetail {
    INVALID_INPUT ("INVALID_INPUT", "Input is invalid.  Please provide valid Integer within the range [1 - 3999]"),
    INVALID_INPUT_RANGE("INVALID_INPUT_RANGE", "Input is out of supported range.  Please provide valid number within the range [1 - 3999]"),
    INPUT_EMPTY("INPUT_EMPTY", "Input is empty.  Please provide valid number within the range [1 - 3999]"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Something went wrong.  Please try again later");
    String errorCode;
    String errorMessage;

    ErrorDetail(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
