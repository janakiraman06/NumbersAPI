package com.numbers.numberconverter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.numbers.numberconverter.enumerations.ErrorDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberConverterError {
    @JsonProperty("code")
    String errorCode;
    @JsonProperty("message")
    String errorMessage;

    public NumberConverterError() {
    }

    public NumberConverterError(ErrorDetail errorDetail) {
        this.errorCode = errorDetail.getErrorCode();
        this.errorMessage = errorDetail.getErrorMessage();
    }

    @Override
    public String toString() {
        return "NumberConverterError{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
