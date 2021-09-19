package com.numbers.numberconverter.service;

import com.numbers.numberconverter.enumerations.ErrorDetail;
import com.numbers.numberconverter.exception.NumberConverterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberConverterServiceImplTest {

    NumberConverterService numberConverterService = new NumberConverterServiceImpl();

    @Test
    void convertToRomanNumeralSuccessScenario() {
        assertEquals("XXXIX", numberConverterService.convertToRomanNumeral(39));
        assertEquals("CCXLVI", numberConverterService.convertToRomanNumeral(246));
        assertEquals("DCCLXXXIX", numberConverterService.convertToRomanNumeral(789));
        assertEquals("MMCDXXI", numberConverterService.convertToRomanNumeral(2421));
        assertEquals("CLX", numberConverterService.convertToRomanNumeral(160));
        assertEquals("CCVII", numberConverterService.convertToRomanNumeral(207));
        assertEquals("MIX", numberConverterService.convertToRomanNumeral(1009));
        assertEquals("MLXVI", numberConverterService.convertToRomanNumeral(1066));
        assertEquals("MDCCLXXVI", numberConverterService.convertToRomanNumeral(1776));
        assertEquals("MCMXVIII", numberConverterService.convertToRomanNumeral(1918));
        assertEquals("MCMLIV", numberConverterService.convertToRomanNumeral(1954));
        assertEquals("MMXIV", numberConverterService.convertToRomanNumeral(2014));
        assertEquals("MMMCMXCIX", numberConverterService.convertToRomanNumeral(3999));
        assertEquals("MMXXI", numberConverterService.convertToRomanNumeral(2021));
    }
    @Test
    void convertToRomanNumeralFailureScenario(){

        NumberConverterException numberConverterException = assertThrows(NumberConverterException.class, ()-> {
            numberConverterService.convertToRomanNumeral(0);
        });
        assertTrue(numberConverterException.getErrorDetail().equals(ErrorDetail.INVALID_INPUT_RANGE));

        numberConverterException = assertThrows(NumberConverterException.class, ()-> {
            numberConverterService.convertToRomanNumeral(-569);
        });
        assertTrue(numberConverterException.getErrorDetail().equals(ErrorDetail.INVALID_INPUT_RANGE));

        numberConverterException = assertThrows(NumberConverterException.class, ()-> {
            numberConverterService.convertToRomanNumeral(54000);
        });
        assertTrue(numberConverterException.getErrorDetail().equals(ErrorDetail.INVALID_INPUT_RANGE));
    }
}