package com.numbers.numberconverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numbers.numberconverter.enums.ErrorDetail;
import com.numbers.numberconverter.model.NumberConverterResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc
@SpringBootTest
class NumbersConverterIntegrationTest {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    MockMvc mockMvc;
    @Test
    void convertToRoman_successScenario() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?query=5").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        String result= mvcResult.getResponse().getContentAsString();
        NumberConverterResponse response = objectMapper.readValue(result, NumberConverterResponse.class);
        assertEquals("V", response.getOutput());
    }
    @Test
    void convertToRoman_inputEmpty() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        String result= mvcResult.getResponse().getContentAsString();
        NumberConverterResponse response = objectMapper.readValue(result, NumberConverterResponse.class);
        assertEquals(ErrorDetail.INPUT_EMPTY.getErrorCode(), response.getNumberConverterError().getErrorCode());
    }
    @Test
    void convertToRoman_outOfRangeNegativeInput() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?query=-1000").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        String result= mvcResult.getResponse().getContentAsString();
        NumberConverterResponse response = objectMapper.readValue(result, NumberConverterResponse.class);
        assertEquals(ErrorDetail.INVALID_INPUT_RANGE.getErrorCode(), response.getNumberConverterError().getErrorCode());
    }
    @Test
    void convertToRoman_outOfRangePositiveInput() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?query=9999").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        String result= mvcResult.getResponse().getContentAsString();
        NumberConverterResponse response = objectMapper.readValue(result, NumberConverterResponse.class);
        assertEquals(ErrorDetail.INVALID_INPUT_RANGE.getErrorCode(), response.getNumberConverterError().getErrorCode());
    }
    @Test
    void convertToRoman_invalidInput() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/romannumeral?query=a2b").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        String result= mvcResult.getResponse().getContentAsString();
        NumberConverterResponse response = objectMapper.readValue(result, NumberConverterResponse.class);
        assertEquals(ErrorDetail.INVALID_INPUT.getErrorCode(), response.getNumberConverterError().getErrorCode());
    }

}
