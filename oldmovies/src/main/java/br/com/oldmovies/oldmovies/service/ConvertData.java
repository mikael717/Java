package br.com.oldmovies.oldmovies.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

public class ConvertData implements IConvertData {

    private ObjectMapper mapper = new ObjectMapper();


    public ConvertData(){
        mapper.registerModule(new JavaTimeModule());
    }
    @Override
    public <T> T obtainData(String json, Class<T> tClass) {
        try {
            return this.mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obtainList(String json, Class<T> tClass) {
        CollectionType list = this.mapper.getTypeFactory().constructCollectionType(List.class, tClass);

        try {
            return this.mapper.readValue(json, list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
