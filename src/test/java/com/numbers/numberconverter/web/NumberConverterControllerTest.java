package com.numbers.numberconverter.web;

import com.numbers.numberconverter.enumerations.ErrorDetail;
import com.numbers.numberconverter.exception.NumberConverterException;
import com.numbers.numberconverter.model.NumberConverterResponse;
import com.numbers.numberconverter.service.NumberConverterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Janakiraman Raghu
 *
 * Unit Test Class for NumberConverterController
 */
@ExtendWith(SpringExtension.class)
class NumberConverterControllerTest {

    @Mock
    NumberConverterService numberConverterService;
    @InjectMocks
    NumberConverterController numberConverterController = new NumberConverterController();

    @Test
    void convertToRomanSuccessScenario() {
        Mockito.when(numberConverterService.convertToRomanNumeral(5)).thenReturn("V");
        NumberConverterResponse result = numberConverterController.convertToRoman(5);
        Assertions.assertEquals("V",result.getOutput());
        Assertions.assertEquals("5",result.getInput());
    }
    @Test
    void convertToRomanFailureScenario(){
        Mockito.when(numberConverterService.convertToRomanNumeral(-48)).thenThrow(new NumberConverterException(ErrorDetail.INVALID_INPUT_RANGE));

        NumberConverterException numberConverterException = assertThrows(NumberConverterException.class, () ->{
            numberConverterController.convertToRoman(-48);
        });
        assertEquals(ErrorDetail.INVALID_INPUT_RANGE, numberConverterException.getErrorDetail());
    }

}