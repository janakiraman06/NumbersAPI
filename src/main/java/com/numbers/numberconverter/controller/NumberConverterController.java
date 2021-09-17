package com.numbers.numberconverter.controller;

import com.numbers.numberconverter.model.NumberConverterResponseDTO;
import com.numbers.numberconverter.service.NumberConverterService;
import com.numbers.numberconverter.service.NumberConverterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/romannumeral")
public class NumberConverterController {
    @Autowired
    NumberConverterService numberConverterService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NumberConverterResponseDTO convertToRoman(@RequestParam("query") int number){
        String romanNumberal = numberConverterService.convertToRomanNumeral(number);
        NumberConverterResponseDTO romanNumeralConverterResponseDTO = new NumberConverterResponseDTO(String.valueOf(number),romanNumberal);
        return romanNumeralConverterResponseDTO;
    }

}
