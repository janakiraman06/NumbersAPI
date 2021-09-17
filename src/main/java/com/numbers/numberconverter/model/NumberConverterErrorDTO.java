package com.numbers.numberconverter.model;

import com.numbers.numberconverter.error.ErrorDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberConverterErrorDTO {
    String errorCode;
    String errorMessage;

    public NumberConverterErrorDTO() {
    }

    public NumberConverterErrorDTO(ErrorDetail errorDetail) {
        this.errorCode = errorDetail.getErrorCode();
        this.errorMessage = errorDetail.getErrorMessage();
    }
}
