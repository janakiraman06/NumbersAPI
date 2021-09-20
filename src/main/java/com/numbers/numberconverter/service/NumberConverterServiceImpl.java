package com.numbers.numberconverter.service;

import com.numbers.numberconverter.enumerations.ErrorDetail;
import com.numbers.numberconverter.exception.NumberConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Janakiraman Raghu
 * This Service has core logic for number conversion
 */
@Service
public class NumberConverterServiceImpl implements NumberConverterService{
    Logger LOGGER = LoggerFactory.getLogger(NumberConverterServiceImpl.class);

    //This collection will be used during the conversion based on digits position
    static List<List<String>>  romanLetters = new ArrayList<>();
    static {
        //Used for last digit(1's position) - Ex: num = 1234, this list would be used for the digit 4
        romanLetters.add(Collections.unmodifiableList(Arrays.asList("I","V","X")));

        //Used for second from last digit(10's position) - Ex: num = 1234, this list would be used for the digit 3
        romanLetters.add(Collections.unmodifiableList(Arrays.asList("X","L","C")));

        //Used for third from last digit(100's position) - Ex: num = 1234, this list would be used for the digit 2
        romanLetters.add(Collections.unmodifiableList(Arrays.asList("C","D","M")));

        //Used for fourth from last digit(1000's position) - Ex: num = 1234, this list would be used for the digit 1
        romanLetters.add(Collections.unmodifiableList(Arrays.asList("M")));

        romanLetters = Collections.unmodifiableList(romanLetters);
    }

    /**
     * The algorithm of the conversion is as follows
     *
     *  1. Last digit of the given number is extracted(n%10)
     *  2. For this digit, the romanNumber is constructed based on the helper method.
     *  3. The result of this conversion is appended to the previous result
     *  4. The input is then reduced by dividing it by 10 (n=n/10)
     *  5. The same logic continues until given number is  > 0
     *
     * @param number
     * @return
     */
    @Override
    public String convertToRomanNumeral(final int number) {
        if(number<1 || number > 3999) {
            LOGGER.error("Input {} is Invalid", number);
            throw new NumberConverterException(ErrorDetail.INVALID_INPUT_RANGE);
        }
        int base = 10;
        String result = new String();
        int  temp = number, index = 0;
        while(temp>0){
            int digit = (temp % base);
            result = convertDigitToRomanNumeral(digit, romanLetters.get(index++)) + result;
            temp=temp/base;
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
    private String convertDigitToRomanNumeral(final int digit, List<String> romanLetters) {
        StringBuilder romanBuilder = new StringBuilder();
        if(digit<=3){
            romanBuilder.append(repeat(romanLetters.get(0), digit));
        }else if(digit == 4){
            romanBuilder.append(romanLetters.get(0)).append(romanLetters.get(1));
        }else if(digit <= 8){
            romanBuilder.append(romanLetters.get(1));
            romanBuilder.append(repeat(romanLetters.get(0), digit-5));
        }else if(digit == 9){
            romanBuilder.append(romanLetters.get(0)).append(romanLetters.get(2));
        }
        LOGGER.debug("Digit = {}, RomanLetters = {}, Result = {}" ,digit ,romanLetters,romanBuilder.toString() );
        return romanBuilder.toString();
    }

    /**
     * String utility method to repeat a given string by that many times
     * Ex: repeat("X", 3) => "XXX"
     * @param romanChar
     * @param counter
     * @return
     */
    private String repeat(String romanChar, int counter) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int index=0;index<counter;index++){
            stringBuilder.append(romanChar);
        }
        return stringBuilder.toString();
    }
}
