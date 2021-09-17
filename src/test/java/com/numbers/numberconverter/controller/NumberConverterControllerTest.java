package com.numbers.numberconverter.controller;

import com.numbers.numberconverter.error.ErrorDetail;
import com.numbers.numberconverter.exception.NumberConverterException;
import com.numbers.numberconverter.model.NumberConverterResponseDTO;
import com.numbers.numberconverter.service.NumberConverterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class NumberConverterControllerTest {

    @Mock
    NumberConverterService numberConverterService;
    @InjectMocks
    NumberConverterController numberConverterController = new NumberConverterController();

    @Test
    void convertToRomanSuccessScenario() {
        Mockito.when(numberConverterService.convertToRomanNumeral(5)).thenReturn("V");
        NumberConverterResponseDTO result = numberConverterController.convertToRoman(5);
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