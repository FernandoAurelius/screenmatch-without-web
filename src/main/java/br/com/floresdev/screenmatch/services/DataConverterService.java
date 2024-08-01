package br.com.floresdev.screenmatch.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverterService implements DataConverter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T convertData(String json, Class<T> returnClass) {
        if (json.isEmpty()) {
            throw new IllegalStateException("JSON can't be empty!");
        }
        try {
            return mapper.readValue(json, returnClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
