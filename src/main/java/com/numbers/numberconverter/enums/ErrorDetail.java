package com.numbers.numberconverter.enums;

public enum ErrorDetail {
    INVALID_INPUT ("INVALID_INPUT", "Input is invalid.  Please provide valid number within the range [1 - 3999]"),
    INVALID_INPUT_RANGE("INVALID_INPUT_RANGE", "Input is out of supported range.  Please provide valid number within the range [1 - 3999]"),
    INPUT_EMPTY("INPUT_EMPTY", "Input is empty.  Please provide valid number within the range [1 - 3999]");
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
