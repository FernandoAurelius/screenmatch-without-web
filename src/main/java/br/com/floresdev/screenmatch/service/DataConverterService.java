package br.com.floresdev.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverterService implements DataConverter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T convertData(String address, Class<T> returnClass) {
        if (address.isEmpty()) {
            throw new IllegalStateException("JSON can't be empty!");
        }
        try {
            return mapper.readValue(ApiConsumeService.getData(address), returnClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
