package com.numbers.numberconverter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NumberConverterResponse {
    private String input;
    private String output;
    @JsonProperty("error")
    private NumberConverterError numberConverterError;

    public NumberConverterResponse(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public NumberConverterResponse(NumberConverterError numberConverterError) {
        this.numberConverterError = numberConverterError;
    }

    public NumberConverterResponse() {
    }

    @Override
    public String toString() {
        return "NumberConverterResponse{" +
                "input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", numberConverterError=" + numberConverterError +
                '}';
    }
}
