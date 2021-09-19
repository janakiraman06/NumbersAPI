package com.numbers.numberconverter.exception;

import com.numbers.numberconverter.enumerations.ErrorDetail;
import lombok.Getter;
import lombok.Setter;

/**
 * This is customer Exception created for NumberConverter application
 */
@Getter
@Setter
public class NumberConverterException extends RuntimeException{
    ErrorDetail errorDetail;
    public NumberConverterException(ErrorDetail errorDetail){
        super(errorDetail.getErrorMessage());
        this.errorDetail = errorDetail;
    }

}
