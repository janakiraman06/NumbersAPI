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
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Iterator;

@ControllerAdvice
public class NumberConverterExceptionHandler {
    Logger LOGGER = LoggerFactory.getLogger(NumberConverterExceptionHandler.class);
    @ExceptionHandler(value = NumberConverterException.class)
    public final ResponseEntity<NumberConverterResponse> handleNumberConverterException(NumberConverterException ex, WebRequest webRequest){
        ServletWebRequest request = (ServletWebRequest) webRequest;
        LOGGER.error("Requested URI: "+request.getRequest().getRequestURI() +" - Input range is invalid. Required input range: [1,3999].  Provided input: "+getQueryParameter(webRequest));
        NumberConverterError error = new NumberConverterError(ex.getErrorDetail());
        NumberConverterResponse response = new NumberConverterResponse(error);
        LOGGER.error(response.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public final ResponseEntity<NumberConverterResponse> handleMissingServletRequestParameterException(WebRequest webRequest){
        ServletWebRequest request = (ServletWebRequest) webRequest;
        LOGGER.error("Requested URI: "+request.getRequest().getRequestURI() +" - Input is empty");
        NumberConverterError error = new NumberConverterError(ErrorDetail.INPUT_EMPTY);
        NumberConverterResponse response = new NumberConverterResponse(error);
        LOGGER.error(response.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TypeMismatchException.class)
    public final ResponseEntity<NumberConverterResponse> handleTypeMismatchException(WebRequest webRequest){
        ServletWebRequest request = (ServletWebRequest) webRequest;
        LOGGER.error("Requested URI: "+request.getRequest().getRequestURI() +" - Provided input \""+getQueryParameter(webRequest) + "\" is invalid.  Input should be Integer in the range [1-3999]");
        NumberConverterError error = new NumberConverterError(ErrorDetail.INVALID_INPUT);
        NumberConverterResponse response = new NumberConverterResponse(error);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<NumberConverterResponse> handleGenericException(Exception exception, WebRequest webRequest){
        ServletWebRequest request = (ServletWebRequest) webRequest;
        LOGGER.error("Requested URI: "+request.getRequest().getRequestURI() +" - input : "+getQueryParameter(webRequest)+ ", exception - " + exception.getMessage());
        NumberConverterError error = new NumberConverterError(ErrorDetail.INTERNAL_SERVER_ERROR);
        NumberConverterResponse response = new NumberConverterResponse(error);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private String getQueryParameter(WebRequest request){

        Iterator<String> iterator = request.getParameterNames();
        StringBuilder sb = new StringBuilder();
        while(iterator.hasNext()){
            String key = iterator.next();
            sb.append(request.getParameter(key));
        }
        return sb.toString();
    }

}
