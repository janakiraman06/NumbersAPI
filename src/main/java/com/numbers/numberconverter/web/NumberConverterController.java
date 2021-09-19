package com.numbers.numberconverter.web;

import com.numbers.numberconverter.model.NumberConverterResponse;
import com.numbers.numberconverter.service.NumberConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Janakiraman Raghu
 * This class exposes the end points for conversion
 */
@RestController
@RequestMapping(path="/romannumeral")
public class NumberConverterController {
    @Autowired
    NumberConverterService numberConverterService;

    /**
     * This method exposes the API for converting Integer to Roman Number
     * @param number
     * @return
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NumberConverterResponse convertToRoman(@RequestParam("query") int number){
        String romanNumber = numberConverterService.convertToRomanNumeral(number);
        return new NumberConverterResponse(String.valueOf(number),romanNumber);
    }
}
