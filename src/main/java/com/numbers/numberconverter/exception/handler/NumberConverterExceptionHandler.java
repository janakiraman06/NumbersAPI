package com.numbers.numberconverter.exception.handler;

import com.numbers.numberconverter.enumerations.ErrorDetail;
import com.numbers.numberconverter.exception.NumberConverterException;
import com.numbers.numberconverter.model.NumberConverterError;
import com.numbers.numberconverter.model.NumberConverterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NumberConverterExceptionHandler {
    Logger LOGGER = LoggerFactory.getLogger(NumberConverterExceptionHandler.class);
    @ExceptionHandler(value = NumberConverterException.class)
    public final ResponseEntity<Object> handleNumberConverterException(NumberConverterException ex){
        NumberConverterError error = new NumberConverterError(ex.getErrorDetail());
        NumberConverterResponse response = new NumberConverterResponse(error);
        LOGGER.error(response.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public final ResponseEntity<NumberConverterResponse> handleMissingServletRequestParameterException(){
        NumberConverterError error = new NumberConverterError(ErrorDetail.INPUT_EMPTY);
        NumberConverterResponse response = new NumberConverterResponse(error);
        LOGGER.error(response.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = TypeMismatchException.class)
    public final ResponseEntity<NumberConverterResponse> handleTypeMismatchException(){
        NumberConverterError error = new NumberConverterError(ErrorDetail.INVALID_INPUT);
        NumberConverterResponse response = new NumberConverterResponse(error);
        LOGGER.error(response.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
