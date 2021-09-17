package com.numbers.numberconverter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NumberConverterResponseDTO {
    private String input;
    private String output;
    @JsonProperty("error")
    private NumberConverterErrorDTO romanNumeralConverterErrorDTO;

    public NumberConverterResponseDTO(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public NumberConverterResponseDTO(NumberConverterErrorDTO romanNumeralConverterErrorDTO) {
        this.romanNumeralConverterErrorDTO = romanNumeralConverterErrorDTO;
    }

    public NumberConverterResponseDTO() {
    }
}
