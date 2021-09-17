package com.numbers.numberconverter.exception.handler;

import com.numbers.numberconverter.error.ErrorDetail;
import com.numbers.numberconverter.exception.NumberConverterException;
import com.numbers.numberconverter.model.NumberConverterErrorDTO;
import com.numbers.numberconverter.model.NumberConverterResponseDTO;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class NumberConverterExceptionHandler {
    @ExceptionHandler(value = NumberConverterException.class)
    public final ResponseEntity<Object> handleNumberConverterException(NumberConverterException ex){
        NumberConverterErrorDTO error = new NumberConverterErrorDTO(ex.getErrorDetail());
        NumberConverterResponseDTO response = new NumberConverterResponseDTO(error);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public final ResponseEntity<NumberConverterResponseDTO> handleMissingServletRequestParameterException(){
        NumberConverterErrorDTO error = new NumberConverterErrorDTO(ErrorDetail.INPUT_EMPTY);
        NumberConverterResponseDTO response = new NumberConverterResponseDTO(error);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = TypeMismatchException.class)
    public final ResponseEntity<NumberConverterResponseDTO> handleTypeMismatchException(){
        NumberConverterErrorDTO error = new NumberConverterErrorDTO(ErrorDetail.INVALID_INPUT);
        NumberConverterResponseDTO response = new NumberConverterResponseDTO(error);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
