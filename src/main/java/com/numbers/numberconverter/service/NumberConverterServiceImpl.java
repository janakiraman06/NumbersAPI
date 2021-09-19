package com.numbers.numberconverter.service;

import com.numbers.numberconverter.enumerations.ErrorDetail;
import com.numbers.numberconverter.exception.NumberConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Janakiraman Raghu
 * This Service layer has the core logic for number conversion
 */
@Service
public class NumberConverterServiceImpl implements NumberConverterService{
    Logger LOGGER = LoggerFactory.getLogger(NumberConverterServiceImpl.class);

    //This collection will be used during the conversion based on digits position
    final static List<List<String>>  romanLetters = new ArrayList<>();
    static {
        //Used for last digit(1's position) - Ex: num = 1234, this list would be used for the digit 4
        romanLetters.add(Arrays.asList("I","V","X"));

        //Used for second from last digit(10's position) - Ex: num = 1234, this list would be used for the digit 3
        romanLetters.add(Arrays.asList("X","L","C"));

        //Used for third from last digit(100's position) - Ex: num = 1234, this list would be used for the digit 2
        romanLetters.add(Arrays.asList("C","D","M"));

        //Used for fourth from last digit(1000's position) - Ex: num = 1234, this list would be used for the digit 1
        romanLetters.add(Arrays.asList("M"));
    }

    /**
     * The algorithm of the conversion is as follows
     *
     *  1. Last digit of the given number is extracted(n%10)
     *  2. For this digit, the romanNumber is constructed based on the helper method.
     *  3. The result of this conversion is appended with the previous result
     *  4. The input is then reduced by dividing it by 10 (n=n/10)
     *  5. The same logic continues until given number is  > 0
     *
     * @param number
     * @return
     */
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

    /**
     * This method does the real conversion that refers the romanLetters Collection and constructs the result in subtractive notation
     *
     * It also receives the correct list for the given digit's position in the input number.
     *
     * Example : If n = 1523, this method would be called 4 times as follows.
     * convertDigitToRomanNumeral(3, [I,V,X]) => III
     * convertDigitToRomanNumeral(2, [X,L,C]) => XX
     * convertDigitToRomanNumeral(5, [C,D,M]) => D
     * convertDigitToRomanNumeral(1, [M])     => M
     *
     * @param digit
     * @param romanLetters
     * @return
     */
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

    /**
     * String utility method to repeat a given string by that many times
     * Ex: repeat("X", 3) => "XXX"
     * @param s
     * @param counter
     * @return
     */
    private String repeat(String s, int counter) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<counter;i++){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
