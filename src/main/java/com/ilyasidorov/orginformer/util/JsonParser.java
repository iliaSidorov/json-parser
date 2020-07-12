package com.ilyasidorov.orginformer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        //register JavaTimeModule(jackson-datatype-jsr310) for correct working with LocalDate
        defaultObjectMapper.registerModule(new JavaTimeModule());
        //false for not to fail if some properties are not defined in POJO
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    public static JsonNode parse(String jsonFilePath) throws IOException {
        return objectMapper.readTree(new File(jsonFilePath));
    }

    public static <T> T fromJson(JsonNode node, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }

    public static <T> List<T> parseToList(String jsonFilePath, Class<T> clazz) throws IOException {
        List<T> list = new ArrayList<>();
        JsonNode rootNode = parse(jsonFilePath);
        for (JsonNode node : rootNode) {
            T t = fromJson(node, clazz);
            list.add(t);
        }
        return list;
    }



}
