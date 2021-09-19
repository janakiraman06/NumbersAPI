package com.numbers.numberconverter.exception;

import com.numbers.numberconverter.enums.ErrorDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberConverterException extends RuntimeException{
    ErrorDetail errorDetail;
    public NumberConverterException(ErrorDetail errorDetail){
        super(errorDetail.getErrorMessage());
        this.errorDetail = errorDetail;
    }

}
