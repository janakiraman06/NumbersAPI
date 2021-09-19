package com.numbers.numberconverter.service;

import com.numbers.numberconverter.enums.ErrorDetail;
import com.numbers.numberconverter.exception.NumberConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NumberConverterServiceImpl implements NumberConverterService{
    Logger LOGGER = LoggerFactory.getLogger(NumberConverterServiceImpl.class);
    static List<List<String>> romanLetters = new ArrayList<>();
    static {
        romanLetters.add(Arrays.asList("I","V","X"));
        romanLetters.add(Arrays.asList("X","L","C"));
        romanLetters.add(Arrays.asList("C","D","M"));
        romanLetters.add(Arrays.asList("M"));
    }
    @Override
    public String convertToRomanNumeral(int number) {
        if(number<1 || number > 3999) {
            LOGGER.error("Input {} is Invalid", number);
            throw new NumberConverterException(ErrorDetail.INVALID_INPUT_RANGE);
        }
            String result = "";
            int  n=number, index = 0;
            while(n>0){
                int digit = n%10;
                result = convertDigitToRomanNumeral(digit, romanLetters.get(index++)) + result;
                n=n/10;
            }
        LOGGER.info("Input Integer = {} \t Output Roman Numeral = {}", number, result);
        return result;
    }
    private String convertDigitToRomanNumeral(int digit, List<String> romanLetters) {
        StringBuilder sb = new StringBuilder();
        if(digit<=3){
            sb.append(repeat(romanLetters.get(0), digit));
        }else if(digit == 4){
            sb.append(romanLetters.get(0)).append(romanLetters.get(1));
        }else if(digit <= 8){
            sb.append(romanLetters.get(1));
            sb.append(repeat(romanLetters.get(0), digit-5));
        }else if(digit == 9){
            sb.append(romanLetters.get(0)).append(romanLetters.get(2));
        }
        return sb.toString();
    }
    private String repeat(String s, int counter) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<counter;i++){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
