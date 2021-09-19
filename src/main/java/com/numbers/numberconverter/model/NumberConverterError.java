package com.numbers.numberconverter.model;

import com.numbers.numberconverter.enums.ErrorDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberConverterError {
    String errorCode;
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
